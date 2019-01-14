package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class Pembayaran {
    @SerializedName("id") private String id;
    @SerializedName("id_wisata") private String id_wisata;
    @SerializedName("id_tiket") private String id_tiket;
    @SerializedName("id_user") private String id_user;
    @SerializedName("totaltiket") private String totaltiket;
    @SerializedName("tanggalpesan") private String tanggalpesan;
    @SerializedName("tanggaltimeout") private String tanggaltimeout;
    @SerializedName("tanggalkunjungan") private String tanggalkunjungan;
    @SerializedName("tanggalbayar") private String tanggalbayar;
    @SerializedName("id_metodebayar") private String id_metodebayar;
    @SerializedName("biayalayanan") private String biayalayanan;
    @SerializedName("totalbayar") private String totalbayar;
    @SerializedName("fee") private String fee;
    @SerializedName("status") private String status;
    @SerializedName("kodepembayaran") private String kodepembayaran;
    @SerializedName("kodetiket") private String kodetiket;
    // tiket
    @SerializedName("namatiket") private String namatiket;
    @SerializedName("jenistiket") private String jenistiket;
    @SerializedName("hargadiskon") private String hargadiskon;
    @SerializedName("nama_metode") private String nama_metode;
    @SerializedName("biaya") private String biayaLayanan;


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

    public String getId_tiket() {
        return id_tiket;
    }

    public void setId_tiket(String id_tiket) {
        this.id_tiket = id_tiket;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTotaltiket() {
        return totaltiket;
    }

    public void setTotaltiket(String totaltiket) {
        this.totaltiket = totaltiket;
    }

    public String getTanggalpesan() {
        return tanggalpesan;
    }

    public void setTanggalpesan(String tanggalpesan) {
        this.tanggalpesan = tanggalpesan;
    }

    public String getTanggalkunjungan() {
        return tanggalkunjungan;
    }

    public void setTanggalkunjungan(String tanggalkunjungan) {
        this.tanggalkunjungan = tanggalkunjungan;
    }

    public String getTanggalbayar() {
        return tanggalbayar;
    }

    public void setTanggalbayar(String tanggalbayar) {
        this.tanggalbayar = tanggalbayar;
    }

    public String getId_metodebayar() {
        return id_metodebayar;
    }

    public void setId_metodebayar(String id_metodebayar) {
        this.id_metodebayar = id_metodebayar;
    }

    public String getBiayalayanan() {
        return biayalayanan;
    }

    public void setBiayalayanan(String biayalayanan) {
        this.biayalayanan = biayalayanan;
    }

    public String getTotalbayar() {
        return totalbayar;
    }

    public void setTotalbayar(String totalbayar) {
        this.totalbayar = totalbayar;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKodepembayaran() {
        return kodepembayaran;
    }

    public void setKodepembayaran(String kodepembayaran) {
        this.kodepembayaran = kodepembayaran;
    }

    public String getKodetiket() {
        return kodetiket;
    }

    public void setKodetiket(String kodetiket) {
        this.kodetiket = kodetiket;
    }

    public String getTanggaltimeout() {
        return tanggaltimeout;
    }

    public void setTanggaltimeout(String tanggaltimeout) {
        this.tanggaltimeout = tanggaltimeout;
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

    public String getHargadiskon() {
        return hargadiskon;
    }

    public void setHargadiskon(String hargadiskon) {
        this.hargadiskon = hargadiskon;
    }

    public String getNama_metode() {
        return nama_metode;
    }

    public void setNama_metode(String nama_metode) {
        this.nama_metode = nama_metode;
    }

    public String getBiayaLayanan() {
        return biayaLayanan;
    }

    public void setBiayaLayanan(String biayaLayanan) {
        this.biayaLayanan = biayaLayanan;
    }
}
