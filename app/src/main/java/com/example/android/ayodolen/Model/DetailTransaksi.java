package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksi {
    @SerializedName("total") String total;
    @SerializedName("sukses") String sukses;
    @SerializedName("menunggu") String menunggu;
    @SerializedName("batal") String batal;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSukses() {
        return sukses;
    }

    public void setSukses(String sukses) {
        this.sukses = sukses;
    }

    public String getMenunggu() {
        return menunggu;
    }

    public void setMenunggu(String menunggu) {
        this.menunggu = menunggu;
    }

    public String getBatal() {
        return batal;
    }

    public void setBatal(String batal) {
        this.batal = batal;
    }
}
