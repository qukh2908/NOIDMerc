package com.example.noidmerchant.Adapter;

import java.util.HashMap;
import java.util.Map;

public class Product {
   private String name,price,image;

    public Product(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("tensp", tensp);
//        result.put("giasp",giasp);
//        result.put("cafe",cafe1);
//        return result;
//    }
}
