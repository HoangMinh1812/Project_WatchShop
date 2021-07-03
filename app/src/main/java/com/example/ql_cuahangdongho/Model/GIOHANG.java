package com.example.ql_cuahangdongho.Model;

public class GIOHANG {

    String id, ten, hinh;
    int gia, soluong;

    public GIOHANG(String id, String ten, String hinh, int gia, int soluong) {
        this.id = id;
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
