package com.example.noidmerchant.Database;

import java.util.Map;

public class DBImport {
    String masp;
    int soluongnhap;
    Map<String, String> ngaynhap;

    public DBImport(String masp, int soluongnhap, Map<String, String> ngaynhap) {
        this.masp = masp;
        this.soluongnhap = soluongnhap;
        this.ngaynhap = ngaynhap;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSoluongnhap() {
        return soluongnhap;
    }

    public void setSoluongnhap(int soluongnhap) {
        this.soluongnhap = soluongnhap;
    }

    public Map<String, String> getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Map<String, String> ngaynhap) {
        this.ngaynhap = ngaynhap;
    }
}


