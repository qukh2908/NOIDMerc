package com.example.noidmerchant.Adapter;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private String imageUrl;
    private String quantity;
    private String description;
    private String key;
    private String cateKey;

    public Product(String name, String price, String imageUrl, String quantity, String description, String key, String cateKey) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.description = description;
        this.key = key;
        this.cateKey = cateKey;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCateKey() {
        return cateKey;
    }

    public void setCateKey(String cateKey) {
        this.cateKey = cateKey;
    }
}

