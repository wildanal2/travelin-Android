package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class KotaRekom {
    @SerializedName("id") private String id;
    @SerializedName("nama") private String nama;
    @SerializedName("img") private String img;
    @SerializedName("lt") private double lt;
    @SerializedName("log") private double log;
    @SerializedName("img_source") private String img_source;


    public KotaRekom(String id, String nama, String img, double lt, double log, String img_source) {
        this.id = id;
        this.nama = nama;
        this.img = img;
        this.lt = lt;
        this.log = log;
        this.img_source = img_source;
    }

    public double getLt() {
        return lt;
    }

    public void setLt(double lt) {
        this.lt = lt;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public String getImg_source() {
        return img_source;
    }

    public void setImg_source(String img_source) {
        this.img_source = img_source;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
