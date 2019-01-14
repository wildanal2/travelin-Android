package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class Komentar {
    @SerializedName("id_komentar") private String id_komentar;
    @SerializedName("id_user") private String id_user;
    @SerializedName("nama") private String nama;
    @SerializedName("id_wisata") private String id_wisata;
    @SerializedName("komentar") private String komentar;
    @SerializedName("tanggal") private String tanggal;

    public String getId_komentar() {
        return id_komentar;
    }

    public void setId_komentar(String id_komentar) {
        this.id_komentar = id_komentar;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
