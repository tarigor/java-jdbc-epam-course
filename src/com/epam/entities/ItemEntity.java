package com.epam.entities;

import com.epam.Main.SQLMain;

public class ItemEntity {
    private String itemName;
    private String itemDescription;
    private Double itemPrice;

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public  ItemEntity(){}

    public ItemEntity(String itemName, String itemDescription, Double itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
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
