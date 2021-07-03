package com.example.ql_cuahangdongho.Model;

public class MENU {

    String tenloai;
    int hinh;

    public MENU(String tenloai, int hinh) {
        this.tenloai = tenloai;
        this.hinh = hinh;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
