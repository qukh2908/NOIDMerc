package com.example.noidmerchant.Adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Product implements Serializable {
    private String name;

    private String price;
    private String imageUrl;
    private String quanl;
    private String des;
    private String nameID;

    public Product(String name, String price, String imageUrl,String quanl,String des,String nameID) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quanl= quanl;
        this.des= des;
        this.nameID = nameID;
    }

    public Product() {
    }

    public String getQuanl() {
        return quanl;
    }

    public void setQuanl(String quanl) {
        this.quanl = quanl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
