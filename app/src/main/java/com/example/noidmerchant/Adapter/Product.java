package com.example.noidmerchant.Adapter;

import java.util.HashMap;
import java.util.Map;

public class Product {
   private String tensp,hinhsp,categoryID,giasp;

    public Product(String tensp, String hinhsp, String categoryID, String giasp) {
        this.tensp = tensp;
        this.hinhsp = hinhsp;
        this.categoryID = categoryID;
        this.giasp = giasp;
    }

    public Product() {
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getCategoryID(String key) {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("tensp", tensp);
//        result.put("giasp",giasp);
//        result.put("hinhsp",hinhsp);
//        return result;
//    }
}
