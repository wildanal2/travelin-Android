package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class TiketResponse {

    @SerializedName("status") private String status;
    @SerializedName("result") private Tiket tiket;
    @SerializedName("message") private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
