package com.epam.entities;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderEntity {
    private int orderId;
    private String items;
    private String orderDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public OrderEntity(){}

    public OrderEntity(int orderId, String items, String orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
    }

    public Integer getAmountOfItemsInOrder() {
        Integer amountOfItemsInOrder=0;
        for (int i = 0; i < items.split("/").length; i++) {
            amountOfItemsInOrder = i+1;
        }
        return amountOfItemsInOrder;
    }

    public List<String> getItemsInOrderAsList() {
        List<String> itemsInOrderAsList  = Arrays.asList(items.split("/"));
        return itemsInOrderAsList;
    }

    @Override
    public String toString() {
        return "Order Information: " +
                "orderId=" + orderId +
                ", items='" + getItemsInOrderAsList() + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ' ';
    }
}
