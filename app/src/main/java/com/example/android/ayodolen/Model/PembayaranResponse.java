package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembayaranResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private Pembayaran mPembayaran;
    @SerializedName("message")
    private String message;
    @SerializedName("kodepembayaran")
    private String kodepembayaran;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pembayaran getmPembayaran() {
        return mPembayaran;
    }

    public void setmPembayaran(Pembayaran mPembayaran) {
        this.mPembayaran = mPembayaran;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKodepembayaran() {
        return kodepembayaran;
    }

    public void setKodepembayaran(String kodepembayaran) {
        this.kodepembayaran = kodepembayaran;
    }
}
