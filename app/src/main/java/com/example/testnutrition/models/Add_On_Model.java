package com.example.testnutrition.models;

import java.io.Serializable;

public class Add_On_Model implements Serializable
{
    int addonimage_Id;
    String add_item_name;
    String imagUrl;
    String price;
    String weight;
    String quantity;
    String type;
    String category;
    String type2;

    public Add_On_Model(String add_item_name, String imagUrl, String price, String weight, String quantity, String type, String category, String type2, String description) {
        this.add_item_name = add_item_name;
        this.imagUrl = imagUrl;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.type = type;
        this.category = category;
        this.type2 = type2;
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public Add_On_Model(String add_item_name, String imagUrl) {
        this.add_item_name = add_item_name;
        this.imagUrl = imagUrl;
    }

    public Add_On_Model(int addonimage_Id, String add_item_name) {
        this.addonimage_Id = addonimage_Id;
        this.add_item_name = add_item_name;
    }

    public int getAddonimage_Id() {
        return addonimage_Id;
    }

    public void setAddonimage_Id(int addonimage_Id) {
        this.addonimage_Id = addonimage_Id;
    }

    public String getAdd_item_name() {
        return add_item_name;
    }

    public void setAdd_item_name(String add_item_name) {
        this.add_item_name = add_item_name;
    }
}
