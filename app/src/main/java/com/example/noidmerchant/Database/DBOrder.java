package com.example.noidmerchant.Database;

import java.util.Map;

public class DBOrder {
    String matk, tinhtrang, ghichu;
    int phigiaohang, tonggiadhm;
    Map<String, String> ngaytaodhm;

    public DBOrder(String matk, String tinhtrang, String ghichu, int phigiaohang,
                   int tonggiadhm, Map<String, String> ngaytaodhm) {
        this.matk = matk;
        this.tinhtrang = tinhtrang;
        this.ghichu = ghichu;
        this.phigiaohang = phigiaohang;
        this.tonggiadhm = tonggiadhm;
        this.ngaytaodhm = ngaytaodhm;
    }

    public String getMatk() {
        return matk;
    }

    public void setMatk(String matk) {
        this.matk = matk;
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

    public int getPhigiaohang() {
        return phigiaohang;
    }

    public void setPhigiaohang(int phigiaohang) {
        this.phigiaohang = phigiaohang;
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
