package com.example.testnutrition.models;

public class ItemList {
    int imageid;
    String itemname;
    String packname;

    public ItemList(int imageid, String itemname, String packname) {
        this.imageid = imageid;
        this.itemname = itemname;
        this.packname = packname;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }
}
