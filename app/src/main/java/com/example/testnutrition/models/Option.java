package com.example.testnutrition.models;

public class Option
{
    String name,logo,color;

    public Option(String name, String logo, String color) {
        this.name = name;
        this.logo = logo;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Option(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
}
