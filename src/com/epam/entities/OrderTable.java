package com.epam.entities;

import java.util.Date;
import java.util.List;

public class OrderTable {
    Double totalOrderPrice;
    private int orderId;
    private Date orderDate;
    private List<String> items;

    public OrderTable() {
    }

    public OrderTable(int orderId, List<String> items, Date orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Integer getAmountOfUniqueItems() {
        Integer amountOfUniqueItems = 0;
        for (int i = 0; i < items.size(); i++) {
            amountOfUniqueItems = i + 1;
        }
        return amountOfUniqueItems;
    }

    @Override
    public String toString() {
        return "Order Information: " +
                "orderId=" + orderId +
                ", items='" + items.toString() + '\'' +
                ", orderDate='" + orderDate + '\'' +
//                ", total Price='" + getTotalPriceOfOrder() + '\'' +
                ' ';
    }
}
