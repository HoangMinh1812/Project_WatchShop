package com.example.ql_cuahangdongho.Model;

public class SANPHAMYEUTHICH {

    String id, ten, mota, hinh;
    int gia;

    public SANPHAMYEUTHICH(String id, String ten, String mota, String hinh, int gia) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.hinh = hinh;
        this.gia = gia;
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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
}
