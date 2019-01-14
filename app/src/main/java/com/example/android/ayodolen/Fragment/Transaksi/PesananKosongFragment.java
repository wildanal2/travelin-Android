package com.example.android.ayodolen.Fragment.Transaksi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.R;


public class PesananKosongFragment extends Fragment {
    View v ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pesanan_kosong, container, false);
        return v;
    }

}
