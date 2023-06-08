package com.example.noidmerchant.Adapter;

import java.util.HashMap;
import java.util.Map;

public class Product {
   private String productName,productPrice,productImage,categoryID;

    public Product(String productName, String productPrice, String productImage, String categoryID) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.categoryID = categoryID;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCategoryID(String key) {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
//        public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("productName", productName);
//        result.put("productPrice",productPrice);
//        result.put("productImage",productImage);
//        return result;
//    }
}
