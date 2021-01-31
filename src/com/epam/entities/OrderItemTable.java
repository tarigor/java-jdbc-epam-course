package com.epam.entities;

public class OrderItemTable {
    private Integer itemOrderId;
    private String itemName;
    private Integer amounts;

    public OrderItemTable(Integer itemOrderId, String items, Integer amounts) {
        this.itemOrderId = itemOrderId;
        this.itemName = items;
        this.amounts = amounts;
    }

    public Integer getItemOrderId() {
        return itemOrderId;
    }

    public void setItemOrderId(Integer itemOrderId) {
        this.itemOrderId = itemOrderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getAmounts() {
        return amounts;
    }

    public void setAmounts(Integer amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "OrderItemTable{" +
                "itemOrderId=" + itemOrderId +
                ", items='" + itemName + '\'' +
                ", amounts=" + amounts +
                '}';
    }
}
