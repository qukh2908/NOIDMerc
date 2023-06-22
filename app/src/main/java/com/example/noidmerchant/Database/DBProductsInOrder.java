package com.example.noidmerchant.Database;

import java.io.Serializable;
public class DBProductsInOrder  implements Serializable {
     String masp, tensp, ghichu, thoigian;
     long soluong, giasp, tongtien;

     public DBProductsInOrder() {

     }
     public DBProductsInOrder(String masp, String tensp, String ghichu, String thoigian, long soluong, long giasp, long tongtien) {
          this.masp = masp;
          this.tensp = tensp;
          this.ghichu = ghichu;
          this.thoigian = thoigian;
          this.soluong = soluong;
          this.giasp = giasp;
          this.tongtien = tongtien;
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

     public long getSoluong() {
          return soluong;
     }

     public void setSoluong(long soluong) {
          this.soluong = soluong;
     }

     public long getGiasp() {
          return giasp;
     }

     public void setGiasp(long giasp) {
          this.giasp = giasp;
     }

     public long getTongtien() {
          return tongtien;
     }

     public void setTongtien(long tongtien) {
          this.tongtien = tongtien;
     }
}
