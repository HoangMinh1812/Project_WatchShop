package com.example.ql_cuahangdongho.Model;

import java.io.Serializable;

public class DANHMUC implements Serializable {

    String iddm, tendm, hinhdm;

    public DANHMUC(String iddm, String tendm, String hinhdm) {
        this.iddm = iddm;
        this.tendm = tendm;
        this.hinhdm = hinhdm;
    }

    public String getIddm() {
        return iddm;
    }

    public void setIddm(String iddm) {
        this.iddm = iddm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public String getHinhdm() {
        return hinhdm;
    }

    public void setHinhdm(String hinhdm) {
        this.hinhdm = hinhdm;
    }
}
