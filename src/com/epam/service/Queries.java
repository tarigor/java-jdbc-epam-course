package com.epam.service;

import com.epam.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queries {
    public List<OrderTable> dataFromOrderTable = new ArrayList<>();
    public List<OrderItemTable> dataFromOrderItemTable = new ArrayList<>();
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final List<ItemTable> dataFromItemTable = new ArrayList<>();
    private Double totalPriceOfOrder;
    private Queries queries;

    //-------------------------------------------------------------------------
    private final String urlWholeItemTable = "SELECT * FROM items";
    private final String urlFilterByItemNameItemTable = "SELECT * FROM items WHERE item_name=?";
    private final String urlFilterByItemDescriptionItemTable = "SELECT * FROM items WHERE item_desc=?";
    private final String urlFilterByItemPriceItemTable = "SELECT * FROM items WHERE item_price=?";
    //-------------------------------------------------------------------------
    private final String urlWholeOrderTable = "SELECT * FROM shop.orders";
    private final String urlFilterByOrderIdOrderTable = "SELECT * FROM shop.orders WHERE order_id=?";
    private final String urlFilterByDateOrderTable = "SELECT * FROM shop.orders WHERE order_date=?";
    //-------------------------------------------------------------------------
    private final String urlWholeOrderItemTable = "SELECT * FROM order_items";
    private final String urlFilterByOrderIdOrderItemTable = "SELECT * FROM order_items WHERE item_order_id=?";
    private final String urlFilterByItemsOrderItemTable = "SELECT * FROM order_items WHERE items=?";
    private final String urlFilterByAmountOrderItemTable = "SELECT * FROM order_items WHERE amount=?";

    //-------------------------------------------------------------------------
    private final String urlAddToOrderTable = "INSERT INTO shop.orders(items,order_date) VALUES(?,?)";
    //-------------------------------------------------------------------------
    private final String urlAddDataToOrderItemTable = "INSERT INTO shop.order_items(item_order_id,items,amount) VALUES(?,?,?)";
    //-------------------------------------------------------------------------
    private final String urlDeleteFromOrderItemTable = "DELETE FROM order_items WHERE item_order_id = ?";
    //-------------------------------------------------------------------------
    private final String urlDeleteFromOrderTable = "DELETE FROM orders WHERE order_id = ?";
    //-------------------------------------------------------------------------

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Queries getQueries() {
        return queries;
    }

    public List<ItemTable> getAllDataFromItemTable() throws SQLException {
        return getDataFromItemTable(getStatement(urlWholeItemTable));
    }

    public List<ItemTable> getDataFromItemTableFilteredByItemName(String itemName) throws SQLException {
        return getDataFromItemTable(getPreparedStatement(urlFilterByItemNameItemTable, itemName));
    }

    public List<ItemTable> getDataFromItemTableFilteredByItemDescription(String itemDescription) throws SQLException {

        return getDataFromItemTable(getPreparedStatement(urlFilterByItemDescriptionItemTable, itemDescription));
    }

    public List<ItemTable> getDataFromItemTableFilteredByItemPrice(Double itemPrice) throws SQLException {
        return getDataFromItemTable(getPreparedStatement(urlFilterByItemPriceItemTable, itemPrice));
    }

    //-------------------------------------------------------------------------
    public List<OrderTable> getAllDataFromOrderTable() throws SQLException {
        return getDataFromOrderTable(getStatement(urlWholeOrderTable));
    }

    public List<OrderTable> getDataFromOrderTableFilteredByOrderId(Integer orderId) throws SQLException {
        return getDataFromOrderTable(getPreparedStatement(urlFilterByOrderIdOrderTable, orderId));
    }

    public List<OrderTable> getDataFromOrderTableFilteredByDate(java.sql.Date date) throws SQLException {
        return getDataFromOrderTable(getPreparedStatement(urlFilterByDateOrderTable, date));
    }

    //-------------------------------------------------------------------------
    public List<OrderItemTable> getAllDataFromOrderItemTable() throws SQLException {
        return getDataFromOrderItemTable(getStatement(urlWholeOrderItemTable));
    }

    public List<OrderItemTable> getDataFromOrderItemTableFilteredByOrderId(Integer orderId) throws SQLException {
        return getDataFromOrderItemTable(getPreparedStatement(urlFilterByOrderIdOrderItemTable, orderId));
    }

    public List<OrderItemTable> getDataFromOrderItemTableFilteredByItemName(String item) throws SQLException {
        return getDataFromOrderItemTable(getPreparedStatement(urlFilterByItemsOrderItemTable, item));
    }

    public List<OrderItemTable> getDataFromOrderItemTableFilteredByAmount(Integer amount) throws SQLException {
        return getDataFromOrderItemTable(getPreparedStatement(urlFilterByAmountOrderItemTable, amount));
    }

    //-------------------------------------------------------------------------
    private ResultSet getStatement(String url) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private ResultSet getPreparedStatement(String url, String tableRow) throws SQLException {
        preparedStatement = connection.prepareStatement(url);
        preparedStatement.setString(1, tableRow);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private ResultSet getPreparedStatement(String url, Double tableRow) throws SQLException {
        preparedStatement = connection.prepareStatement(url);
        preparedStatement.setDouble(1, tableRow);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private ResultSet getPreparedStatement(String url, Integer tableRow) throws SQLException {
        preparedStatement = connection.prepareStatement(url);
        preparedStatement.setInt(1, tableRow);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private ResultSet getPreparedStatement(String url, java.sql.Date tableRow) throws SQLException {
        preparedStatement = connection.prepareStatement(url);
        preparedStatement.setDate(1, tableRow);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    private List<ItemTable> getDataFromItemTable(ResultSet resultSet) throws SQLException {
        dataFromItemTable.clear();
        while (resultSet.next()) {
            dataFromItemTable.add(new ItemTable(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3)));
        }
//        System.out.println(dataFromItemTable.toString());
        return dataFromItemTable;
    }

    private List<OrderTable> getDataFromOrderTable(ResultSet resultSet) throws SQLException {
        dataFromOrderTable.clear();
        while (resultSet.next()) {
            dataFromOrderTable.add(new OrderTable(resultSet.getInt(1), Arrays.asList(resultSet.getString(2).split("/")), resultSet.getDate(3)));
        }
//        System.out.println(dataFromOrderTable.toString());
        return dataFromOrderTable;
    }

    private List<OrderItemTable> getDataFromOrderItemTable(ResultSet resultSet) throws SQLException {
        dataFromOrderItemTable.clear();
        while (resultSet.next()) {
            dataFromOrderItemTable.add(new OrderItemTable(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
        }
//        System.out.println(dataFromOrderItemTable.toString());
        return dataFromOrderItemTable;
    }

    //-------------------------------------------------------------------------
    public Double getTotalPriceOfOrder(Integer orderId) throws SQLException {
        totalPriceOfOrder = 0.0;
        for (OrderItemTable order : getDataFromOrderItemTableFilteredByOrderId(orderId)) {
            for (ItemTable item : getAllDataFromItemTable()) {
                if (order.getItemName().contentEquals(item.getItemName())) {
                    totalPriceOfOrder = totalPriceOfOrder + item.getItemPrice() * order.getAmounts();
                }
            }
        }
        return totalPriceOfOrder;
    }

    //-------------------------------------------------------------------------
    public Boolean addNewOrder(NewOrder order) throws SQLException {
        String items = "";
        for (int i = 0; i < order.getOrderItemList().size(); i++) {
            if (order.getOrderItemList().size() > 0 && i != order.getOrderItemList().size() - 1) {
                items = items + order.getOrderItemList().get(i).getItemName() + "/";
            } else {
                items = items + order.getOrderItemList().get(i).getItemName();
            }
        }
        addDataToOrderTable(items);
        addDataToOrderItemTable(order.getOrderItemList());
        return false;
    }

    private void addDataToOrderTable(String items) {
        try {
            preparedStatement = connection.prepareStatement(urlAddToOrderTable);
            preparedStatement.setString(1, items);
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDataToOrderItemTable(List<OrderItemList> orderItemLists) throws SQLException {
        for (OrderItemList orderItemList : orderItemLists) {
            preparedStatement = connection.prepareStatement(urlAddDataToOrderItemTable);
            preparedStatement.setInt(1, getAllDataFromOrderTable().get(getAllDataFromOrderTable().size() - 1).getOrderId());
            preparedStatement.setString(2, orderItemList.getItemName());
            preparedStatement.setInt(3, orderItemList.getAmount());
            preparedStatement.executeUpdate();
        }
    }

    private void deleteRowsFromOrderItemTable(Integer itemOrderId) throws SQLException {
        preparedStatement = connection.prepareStatement(urlDeleteFromOrderItemTable);
        preparedStatement.setInt(1, itemOrderId);
        preparedStatement.executeUpdate();
    }

    private void deleteRowsFromOrderTable(Integer itemOrderId) throws SQLException {
        preparedStatement = connection.prepareStatement(urlDeleteFromOrderTable);
        preparedStatement.setInt(1, itemOrderId);
        preparedStatement.executeUpdate();
    }

    //-------------------------------------------------------------------------
    //TASK2
    public boolean getOrderPriceLimitedAndConsistingCertainUniqueItems(Queries queries, Double limitOfTotalOrderPrice, Integer limitAmountOfUniqueItems) throws SQLException {
        for (OrderTable order : getAllDataFromOrderTable()) {
            int itemCount = 0;
            if (getTotalPriceOfOrder(order.getOrderId()) < limitOfTotalOrderPrice) {
                for (OrderItemTable orderItemTable : getDataFromOrderItemTableFilteredByOrderId(order.getOrderId())) {
                    itemCount++;
                    if (itemCount >= limitAmountOfUniqueItems) {
                        break;
                    }
                }
            }
            if (itemCount < limitAmountOfUniqueItems && itemCount != 0)
                System.out.println("The order Id as per request: " + order.getOrderId());
        }
        return false;
    }

    //TASK3
    public boolean getOrdersNumbersConsistCertainItem(String itemName) throws SQLException {
        for (OrderItemTable order : getDataFromOrderItemTableFilteredByItemName(itemName)) {
            System.out.println("The order ID as per request: " + order.getItemOrderId());
        }
        return false;
    }

    //TASK4
    public boolean getOrderNumbersWhichNotConsistOfCertainItemWithinCurrentDay(String itemName) throws SQLException {
        Integer tempId = null;
        for (OrderTable orderTable : getAllDataFromOrderTable()) {
            if (orderTable.getOrderDate().toString().contentEquals(new Timestamp(System.currentTimeMillis()).toString().split(" ")[0])) {
                tempId = orderTable.getOrderId();
                for (OrderItemTable orderItemTable : getDataFromOrderItemTableFilteredByOrderId(orderTable.getOrderId())) {
                    if (orderItemTable.getItemName().contentEquals(itemName)) {
                        tempId = null;
                        break;
                    }
                }
            }
        }
        System.out.println("The order ID as per request: " + tempId);
        return false;
    }

    //TASK5
    public boolean createNewOrderOfItemsOrderedWithinCurrentDay() throws SQLException {
        List<OrderItemList> orderItemLists = new ArrayList<>();
        for (OrderTable orderTable : getAllDataFromOrderTable()) {
            if (orderTable.getOrderDate().toString().contentEquals(new Timestamp(System.currentTimeMillis()).toString().split(" ")[0])) {
                for (OrderItemTable orderItemTable : getAllDataFromOrderItemTable()) {
                    if (orderItemTable.getItemOrderId().compareTo(orderTable.getOrderId()) == 0) {
                        orderItemLists.add(new OrderItemList(orderItemTable.getItemName(), orderItemTable.getAmounts()));
                    }
                }
            }
        }
        addNewOrder(new NewOrder(orderItemLists));
        return false;
    }

    //TASK6
    public boolean removeAllOrdersWhereExistCertainAmountOfCertainItems(String itemName, Integer amount) throws SQLException {
        for (OrderItemTable orderItemTable : getAllDataFromOrderItemTable()) {
            if (orderItemTable.getItemName().contentEquals(itemName) && orderItemTable.getAmounts().compareTo(amount) == 0) {
                deleteRowsFromOrderTable(orderItemTable.getItemOrderId());
                deleteRowsFromOrderItemTable(orderItemTable.getItemOrderId());
            }
        }
        return false;
    }
}
