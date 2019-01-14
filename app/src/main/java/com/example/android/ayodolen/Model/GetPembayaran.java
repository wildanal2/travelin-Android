package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPembayaran {

    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Pembayaran> pembayaranList;
    @SerializedName("message") String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pembayaran> getPembayaranList() {
        return pembayaranList;
    }

    public void setPembayaranList(List<Pembayaran> pembayaranList) {
        this.pembayaranList = pembayaranList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
