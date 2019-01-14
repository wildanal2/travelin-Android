package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 15/11/2018.
 */

public class Wisata {
    @SerializedName("id_wisata") private String id_wisata;
    @SerializedName("nama_wisata") private String nama_wisata;
    @SerializedName("id_kategori") private String id_kategori;
    @SerializedName("alamat") private  String alamat;
    @SerializedName("deskripsi") private String deskripsi;
    @SerializedName("longitude") private double longitude;
    @SerializedName("latitude") private double latitude;
    @SerializedName("image") private String image;
    @SerializedName("jarakdarikota") private String jarakdarikota;
    @SerializedName("lokasi") private String lokasi;
    @SerializedName("rating") private String rating;
    @SerializedName("jumlahulasan") private String jumlahulasan;


    public String getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJarakdarikota() {
        return jarakdarikota;
    }

    public void setJarakdarikota(String jarakdarikota) {
        this.jarakdarikota = jarakdarikota;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getJumlahulasan() {
        return jumlahulasan;
    }

    public void setJumlahulasan(String jumlahulasan) {
        this.jumlahulasan = jumlahulasan;
    }
}
