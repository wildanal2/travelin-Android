package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTiket {

    @SerializedName("status") String status;
    @SerializedName("result") List<Tiket> tiketList;
    @SerializedName("message") String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tiket> getTiketList() {
        return tiketList;
    }

    public void setTiketList(List<Tiket> tiketList) {
        this.tiketList = tiketList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
