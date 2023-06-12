package com.example.noidmerchant.Database;

import java.util.Map;

public class DBImport {
    String masp;
    int soluongnhap;
    String ngaynhap;

    public DBImport(String masp, int soluongnhap, String ngaynhap) {
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

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }
}


