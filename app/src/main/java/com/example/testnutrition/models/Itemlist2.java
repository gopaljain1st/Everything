package com.example.testnutrition.models;

public class Itemlist2 {
    int itemimageid;
    String id;
    String packageid;
    String itemname;
    String price,weight;
    String itemquantity;
    String itemcalories;
    String type;


    public Itemlist2(int itemimageid,String id, String packageid, String itemname, String price, String weight, String itemquantity, String type) {
        this.itemimageid = itemimageid;
        this.id = id;
        this.packageid = packageid;
        this.itemname = itemname;
        this.price = price;
        this.weight = weight;
        this.itemquantity = itemquantity;
        this.type = type;
    }



    public Itemlist2(int itemimageid, String itemname, String itemquantity, String itemcalories) {
        this.itemimageid = itemimageid;
        this.itemname = itemname;
        this.itemquantity = itemquantity;
        this.itemcalories = itemcalories;
    }

    public int getItemimageid() {
        return itemimageid;
    }

    public void setItemimageid(int itemimageid) {
        this.itemimageid = itemimageid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getItemcalories() {
        return itemcalories;
    }

    public void setItemcalories(String itemcalories) {
        this.itemcalories = itemcalories;
    }
}
