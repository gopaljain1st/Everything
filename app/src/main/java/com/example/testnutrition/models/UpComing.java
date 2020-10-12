package com.example.testnutrition.models;

public class UpComing
{
    String date,licenseNo,name,price,desc,otherInformations,protein,offers,imgUrl;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UpComing() {
    }

    public UpComing(String name, String desc, String imgUrl) {
        this.name = name;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    public UpComing(String date, String name, String desc, String imgUrl) {
        this.date = date;
        this.name = name;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    public UpComing(String date, String licenseNo, String name, String price, String desc, String otherInformations, String protein, String offers, String imgUrl) {
        this.date = date;
        this.licenseNo = licenseNo;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.otherInformations = otherInformations;
        this.protein = protein;
        this.offers = offers;
        this.imgUrl = imgUrl;
    }
}
