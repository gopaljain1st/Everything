package com.example.testnutrition.models;

public class Item
{
     private String imageUrl,name,weight,quantity;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Item(String imageUrl, String name, String weight, String quantity) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
    }
}
