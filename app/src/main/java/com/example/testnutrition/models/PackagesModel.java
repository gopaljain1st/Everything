package com.example.testnutrition.models;

import java.io.Serializable;

public class PackagesModel implements Serializable
{

    Integer images;
    String id,resturantId,name,category,type,date,time,price,type2,description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public PackagesModel(Integer images, String id, String resturantId, String name, String category, String type, String date, String time, String price, String type2, String description) {
        this.images = images;
        this.id = id;
        this.resturantId = resturantId;
        this.name = name;
        this.category = category;
        this.type = type;
        this.date = date;
        this.time = time;
        this.price = price;
        this.type2 = type2;
        this.description = description;
    }
    public PackagesModel(Integer images, String price) {
        this.images = images;
        this.price = price;
    }

    public Integer getImages() {
        return images;
    }

    public void setImages(Integer images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
