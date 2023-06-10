package com.example.noidmerchant.Database;

import java.util.Map;

public class DBOrder {
    String matk, maphi, tinhtrang, ghichu;
    int tonggiadhm;
    Map<String, String> ngaytaodhm;

    public DBOrder(String matk, String maphi, String tinhtrang, String ghichu, int tonggiadhm, Map<String, String> ngaytaodhm) {
        this.matk = matk;
        this.maphi = maphi;
        this.tinhtrang = tinhtrang;
        this.ghichu = ghichu;
        this.tonggiadhm = tonggiadhm;
        this.ngaytaodhm = ngaytaodhm;
    }

    public String getMatk() {
        return matk;
    }

    public void setMatk(String matk) {
        this.matk = matk;
    }

    public String getMaphi() {
        return maphi;
    }

    public void setMaphi(String maphi) {
        this.maphi = maphi;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getTonggiadhm() {
        return tonggiadhm;
    }

    public void setTonggiadhm(int tonggiadhm) {
        this.tonggiadhm = tonggiadhm;
    }

    public Map<String, String> getNgaytaodhm() {
        return ngaytaodhm;
    }

    public void setNgaytaodhm(Map<String, String> ngaytaodhm) {
        this.ngaytaodhm = ngaytaodhm;
    }
}
