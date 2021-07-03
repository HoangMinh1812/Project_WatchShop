package com.example.ql_cuahangdongho.Model;

import java.io.Serializable;

public class DONGHO implements Serializable {

    String id, ten, xuatxu, gioitinh, idloai, idthuonghieu, hinh;
    int gia;

    public DONGHO(String id, String ten, String xuatxu, String gioitinh, String idloai, String idthuonghieu, String hinh, int gia) {
        this.id = id;
        this.ten = ten;
        this.xuatxu = xuatxu;
        this.gioitinh = gioitinh;
        this.idloai = idloai;
        this.idthuonghieu = idthuonghieu;
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

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getIdloai() {
        return idloai;
    }

    public void setIdloai(String idloai) {
        this.idloai = idloai;
    }

    public String getIdthuonghieu() {
        return idthuonghieu;
    }

    public void setIdthuonghieu(String idthuonghieu) {
        this.idthuonghieu = idthuonghieu;
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
