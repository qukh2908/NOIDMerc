package com.example.noidmerchant.Adapter;

import java.util.Map;

public class Cart {
    private String madh, makh, masp, tensp, ghichu, thoigian;
    private int soluong,giasp, tongtien;
    Map<String, Cart> sanpham;

    public Cart(String madh, String makh, String masp, String tensp, String ghichu, String thoigian, int soluong, int giasp, int tongtien, Map<String, Cart> sanpham) {
        this.madh = madh;
        this.makh = makh;
        this.masp = masp;
        this.tensp = tensp;
        this.ghichu = ghichu;
        this.thoigian = thoigian;
        this.soluong = soluong;
        this.giasp = giasp;
        this.tongtien = tongtien;
        this.sanpham = sanpham;
    }

    public Cart() {
    }

    public String getMadh() {
        return madh;
    }

    public void setMadh(String madh) {
        this.madh = madh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public Map<String, Cart> getSanpham() {
        return sanpham;
    }

    public void setSanpham(Map<String, Cart> sanpham) {
        this.sanpham = sanpham;
    }
}
