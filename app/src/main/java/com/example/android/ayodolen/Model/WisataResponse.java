package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WisataResponse {
    @SerializedName("status") String status;
    @SerializedName("result") Wisata wisataget;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Wisata getWisataget() {
        return wisataget;
    }

    public void setWisataget(Wisata wisataget) {
        this.wisataget = wisataget;
    }
}
