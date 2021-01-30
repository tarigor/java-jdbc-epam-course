package com.epam.service;

import com.epam.entities.ItemEntity;
import com.epam.entities.OrderEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queries {
    private String orderInformationRequest = "SELECT * FROM shop.order WHERE order_id=?";
    private String itemsInformationRequest = "SELECT * FROM shop.items";
    private String orderIdRowFromOrderTable = "SELECT order_id FROM shop.order";
    private String orderTableRequest = "SELECT * FROM shop.order";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private OrderEntity orderEntity;
    private ArrayList<Integer> ordersIdOfAllOrders;
    private Integer totalAmountOfOrdersInShop;
    static List<ItemEntity> itemsBase = new ArrayList<>();
    private ArrayList<OrderEntity> allOrdersInformation;
    private Queries queryInstance;

    public static List<ItemEntity> getItemsBase() {
        return itemsBase;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<ItemEntity> getBaseOfAllItemsInShop() {
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(itemsInformationRequest);
            while (resultSet.next()) {
                itemsBase.add(new ItemEntity(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsBase;
    }

    public Integer getTotalAmountOfOrdersInShop() {
        totalAmountOfOrdersInShop = 0;
        ordersIdOfAllOrders = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(orderIdRowFromOrderTable);
            while (resultSet.next()) {
                Integer orderId = resultSet.getInt(1);
                totalAmountOfOrdersInShop++;
                ordersIdOfAllOrders.add(orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalAmountOfOrdersInShop;
    }

    public ArrayList<OrderEntity> getAllOrdersInformation() {
        allOrdersInformation = new ArrayList<OrderEntity>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(orderTableRequest);
            while (resultSet.next()) {
                allOrdersInformation.add(new OrderEntity(
                        resultSet.getInt(1),
                        Arrays.asList(resultSet.getString(2).split("/")),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allOrdersInformation;
    }


    public void getOrderInformation(Integer orderId) {
        getBaseOfAllItemsInShop();//refresh base of all items in shop, before request execute
        try {
            preparedStatement = connection.prepareStatement(orderInformationRequest);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt("order_id");
                orderEntity = new OrderEntity(orderId, Arrays.asList(resultSet.getString(2).split("/")), resultSet.getString(3));
                System.out.println(orderEntity.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getOrderPriceLimitedAndConsistingCertainUniqueItems(Queries queryInstance, Double limitOfTotalOrderPrice, Integer limitAmountOfUniqueItems) {
        int orderIdAsPerRequest = 0;
        getBaseOfAllItemsInShop();//refresh base of all items in shop, before request execute
        for (int i = 1; i <= getTotalAmountOfOrdersInShop(); i++) {
            getOrderInformation(ordersIdOfAllOrders.get(i - 1));
            if (orderEntity.getTotalPriceOfOrder() < limitOfTotalOrderPrice & orderEntity.getAmountOfUniqueItems() < limitAmountOfUniqueItems)
                orderIdAsPerRequest = orderEntity.getOrderId();
        }
        System.out.println("Order ID as per request: " + orderIdAsPerRequest);
        return false;
    }

    public boolean getOrdersNumbersConsistCertainItem(String itemName) {
        getAllOrdersInformation();
        for (OrderEntity orderInformation : allOrdersInformation) {
            for (String item : orderInformation.getItems()) {
                if (item.contentEquals(itemName))
                    System.out.println("Order " + orderInformation.getOrderId() + " consists item-" + itemName);
            }
        }
        return false;
    }

    boolean getOrderNumbersWhichNotConsistOfCertainItemWithinCurrentDay() {
        return false;
    }

    boolean createNewOrderOfItemsOrderedWithinCurrentDay() {
        return false;
    }

    boolean removeAllOrdersWhereExistCertainAmountOfCertainItems() {
        return false;
    }


}
