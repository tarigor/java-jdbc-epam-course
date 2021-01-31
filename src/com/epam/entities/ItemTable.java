package com.epam.entities;

public class ItemTable {
    private String itemName;
    private String itemDescription;
    private Double itemPrice;

    public ItemTable() {
    }

    public ItemTable(String itemName, String itemDescription, Double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return "Items Base consist: {" +
                "itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
