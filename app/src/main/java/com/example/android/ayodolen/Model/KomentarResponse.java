package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KomentarResponse {
    @SerializedName("status") private String status;
    @SerializedName("result") private List<Komentar> komentarList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Komentar> getKomentarList() {
        return komentarList;
    }

    public void setKomentarList(List<Komentar> komentarList) {
        this.komentarList = komentarList;
    }
}
