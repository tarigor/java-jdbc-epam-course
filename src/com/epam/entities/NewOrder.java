package com.epam.entities;

import java.util.List;

public class NewOrder {
    List<OrderItemList> orderItemList;

    public NewOrder(List<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OrderItemList> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
