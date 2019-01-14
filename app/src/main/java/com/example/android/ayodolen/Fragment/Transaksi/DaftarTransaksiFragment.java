package com.example.android.ayodolen.Fragment.Transaksi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.Adapter.RecyclerAdapterPilihTiket;
import com.example.android.ayodolen.Adapter.RecyclerAdapterTransaksi;
import com.example.android.ayodolen.BayarActivity;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.Listener.ClickListener;
import com.example.android.ayodolen.Listener.RecyclerTouchListener;
import com.example.android.ayodolen.Model.GetPembayaran;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Model.Tiket;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarTransaksiFragment extends Fragment {
    View v;SessionManagement ses;
    ApiInterface mApiInterface;
    HomeActivity mActv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_daftar_transaksi, container, false);
        mActv = (HomeActivity) getActivity();

        ses = new SessionManagement(getContext());
        initLayout();
        loadData();
        initListener();
        return v;
    }


    void initLayout(){


    }

    private void loadData() {

    }


    private void initListener() {


    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }
}
