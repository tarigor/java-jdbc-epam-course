package com.epam.service;

import com.epam.entities.OrderEntity;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;

public class QueryInstance {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String orderInformationRequest="SELECT * FROM shop.order WHERE order_id=?";

    public void getOrderInformation(Connection connection, Integer orderId){
        try {
            preparedStatement = connection.prepareStatement(orderInformationRequest);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                OrderEntity orderEntity = new OrderEntity(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                System.out.println(orderEntity.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean getOrderPriceLimitedAndConsistingCertainUniqueItems() {
        return false;
    }

    boolean getOrderNumbersOfCertainItem() {
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
