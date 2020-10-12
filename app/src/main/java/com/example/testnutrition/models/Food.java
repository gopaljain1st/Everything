package com.example.testnutrition.models;

import java.io.Serializable;

public class Food implements Serializable
{
    String name,price,desc,otherInformations,protein,offers,imgUrl,type,time,itemCount,thaliId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Food() {
    }

    public Food(String name, String price, String protein, String offers, String imgUrl) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.offers = offers;
        this.imgUrl = imgUrl;
    }

    public Food(String name, String price, String protein, String imgUrl) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.imgUrl = imgUrl;
    }

    public String getThaliId() {
        return thaliId;
    }

    public void setThaliId(String thaliId) {
        this.thaliId = thaliId;
    }

    public Food(String name, String price, String protein, String imgUrl, String type, String time, String itemCount, String thaliId) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.imgUrl = imgUrl;
        this.type = type;
        this.time = time;
        this.itemCount = itemCount;
        this.thaliId=thaliId;
    }

    public Food(String name, String price, String desc, String otherInformations, String protein, String offers) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.otherInformations = otherInformations;
        this.protein = protein;
        this.offers = offers;
    }

    public Food(String name, String price, String protein) {
        this.name = name;
        this.price = price;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOtherInformations() {
        return otherInformations;
    }

    public void setOtherInformations(String otherInformations) {
        this.otherInformations = otherInformations;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }
}
