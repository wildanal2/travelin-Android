package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class Tiket {

    @SerializedName("id") private String id;
    @SerializedName("id_wisata") private String id_wisata;
    @SerializedName("namatiket") private String namatiket;
    @SerializedName("jenistiket") private String jenistiket;
    @SerializedName("class") private String kelas;
    @SerializedName("hargaawal") private String hargaawal;
    @SerializedName("hargadiskon") private String hargadiskon;
    @SerializedName("totaltiket") private String totaltiket;

    public Tiket(String id, String id_wisata, String namatiket, String jenistiket, String kelas, String hargaawal, String hargadiskon, String totaltiket) {
        this.id = id;
        this.id_wisata = id_wisata;
        this.namatiket = namatiket;
        this.jenistiket = jenistiket;
        this.kelas = kelas;
        this.hargaawal = hargaawal;
        this.hargadiskon = hargadiskon;
        this.totaltiket = totaltiket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getNamatiket() {
        return namatiket;
    }

    public void setNamatiket(String namatiket) {
        this.namatiket = namatiket;
    }

    public String getJenistiket() {
        return jenistiket;
    }

    public void setJenistiket(String jenistiket) {
        this.jenistiket = jenistiket;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getHargaawal() {
        return hargaawal;
    }

    public void setHargaawal(String hargaawal) {
        this.hargaawal = hargaawal;
    }

    public String getHargadiskon() {
        return hargadiskon;
    }

    public void setHargadiskon(String hargadiskon) {
        this.hargadiskon = hargadiskon;
    }

    public String getTotaltiket() {
        return totaltiket;
    }

    public void setTotaltiket(String totaltiket) {
        this.totaltiket = totaltiket;
    }
}
