package com.epam.entities;

import com.epam.Main.SQLMain;
import com.epam.service.Queries;

import java.util.List;

public class OrderEntity {
    private int orderId;
    private String orderDate;
    private List<String> items;
    Double totalOrderPrice;

    public int getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public OrderEntity() {
    }



    public OrderEntity(int orderId, List<String> items, String orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
    }

    public Integer getAmountOfUniqueItems() {
        Integer amountOfUniqueItems = 0;
        for (int i = 0; i < items.size(); i++) {
            amountOfUniqueItems = i + 1;
        }
        return amountOfUniqueItems;
    }

    public Double getTotalPriceOfOrder() {
        totalOrderPrice = 0.0;
        for (String item : items) {
            for (ItemEntity itemFromBase : Queries.getItemsBase()) {
                if (item.contentEquals(itemFromBase.getItemName())) {
                    totalOrderPrice = totalOrderPrice + itemFromBase.getItemPrice();
                }
            }
        }
        return totalOrderPrice;
    }

    @Override
    public String toString() {
        return "Order Information: " +
                "orderId=" + orderId +
                ", items='" + items.toString() + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", total Price='" + getTotalPriceOfOrder() + '\'' +
                ' ';
    }
}
