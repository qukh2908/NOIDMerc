package com.example.noidmerchant.Adapter;

import java.util.HashMap;
import java.util.Map;

public class Orders {
    String makh, madh, thoigiandh, ghichudh, tinhtrang,nameID;
    int soluongdh, tongtiendh;
    private Map<String, Cart> sanpham;

    public Orders(String makh, String madh, String thoigiandh, String ghichudh, String tinhtrang, String nameID, int soluongdh, int tongtiendh, Map<String, Cart> sanpham) {
        this.makh = makh;
        this.madh = madh;
        this.thoigiandh = thoigiandh;
        this.ghichudh = ghichudh;
        this.tinhtrang = tinhtrang;
        this.nameID = nameID;
        this.soluongdh = soluongdh;
        this.tongtiendh = tongtiendh;
        this.sanpham = sanpham;
    }

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

    public String getNameID(String key) {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public int getSoluongdh() {
        return soluongdh;
    }

    public void setSoluongdh(int soluongdh) {
        this.soluongdh = soluongdh;
    }

    public int getTongtiendh() {
        return tongtiendh;
    }

    public void setTongtiendh(int tongtiendh) {
        this.tongtiendh = tongtiendh;
    }

    public Map<String, Cart> getSanpham() {
        return sanpham;
    }

    public void setSanpham(Map<String, Cart> sanpham) {
        this.sanpham = sanpham;
    }

    public Orders() {
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("makh", makh);
        result.put("madh", madh);
        result.put("tinhtrang", tinhtrang);
        result.put("thoigiandh", thoigiandh);
        result.put("tongtiendh", tongtiendh);


        return result;
    }

}
