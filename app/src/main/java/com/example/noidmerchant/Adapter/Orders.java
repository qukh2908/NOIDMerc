package com.example.noidmerchant.Adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Orders implements Serializable {
    String makh, madh, thoigiandh, ghichudh, tinhtrang, tenkh;
    long soluongdh, tongtiendh;
    //Map<String, Cart> sanpham;
    public Orders() { }

    public Orders(String makh, String madh, String thoigiandh, String ghichudh, String tinhtrang, String tenkh, long soluongdh, long tongtiendh) {
        this.makh = makh;
        this.madh = madh;
        this.thoigiandh = thoigiandh;
        this.ghichudh = ghichudh;
        this.tinhtrang = tinhtrang;
        this.tenkh = tenkh;
        this.soluongdh = soluongdh;
        this.tongtiendh = tongtiendh;
    }

    //    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("makh", makh);
//        result.put("madh", madh);
//        result.put("tinhtrang", tinhtrang);
//        result.put("thoigiandh", thoigiandh);
//        result.put("tongtiendh", tongtiendh);
//        return result;
//    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMadh() {
        return madh;
    }

    public void setMadh(String madh) {
        this.madh = madh;
    }

    public String getThoigiandh() {
        return thoigiandh;
    }

    public void setThoigiandh(String thoigiandh) {
        this.thoigiandh = thoigiandh;
    }

    public String getGhichudh() {
        return ghichudh;
    }

    public void setGhichudh(String ghichudh) {
        this.ghichudh = ghichudh;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public long getSoluongdh() {
        return soluongdh;
    }

    public void setSoluongdh(long soluongdh) {
        this.soluongdh = soluongdh;
    }

    public long getTongtiendh() {
        return tongtiendh;
    }

    public void setTongtiendh(long tongtiendh) {
        this.tongtiendh = tongtiendh;
    }


//    public Map<String, Cart> getSanpham() {
//        return sanpham;
//    }
//
//    public void setSanpham(Map<String, Cart> sanpham) {
//        this.sanpham = sanpham;
//    }
}
