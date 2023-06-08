package com.example.noidmerchant.Database;

public class DBProduct {
    String madm, hinhsp, tensp, motasp;
    int giasp, soluongsp;

    public DBProduct(String madm, String hinhsp, String tensp, String motasp, int giasp, int soluongsp) {
        this.madm = madm;
        this.hinhsp = hinhsp;
        this.tensp = tensp;
        this.motasp = motasp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
    }

    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
