package com.example.noidmerchant.Database;

public class DBExport {
    String matk, masp;
    int soluongxb, giaxb;
    String madhm;

    public DBExport(String matk, String masp, int soluongxb, int giaxb, String madhm) {
        this.matk = matk;
        this.masp = masp;
        this.soluongxb = soluongxb;
        this.giaxb = giaxb;
        this.madhm = madhm;
    }

    public String getMatk() {
        return matk;
    }

    public void setMatk(String matk) {
        this.matk = matk;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSoluongxb() {
        return soluongxb;
    }

    public void setSoluongxb(int soluongxb) {
        this.soluongxb = soluongxb;
    }

    public int getGiaxb() {
        return giaxb;
    }

    public void setGiaxb(int giaxb) {
        this.giaxb = giaxb;
    }

    public String getMadhm() {
        return madhm;
    }

    public void setMadhm(String madhm) {
        this.madhm = madhm;
    }
}
