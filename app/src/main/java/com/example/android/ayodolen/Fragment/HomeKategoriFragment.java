package com.example.android.ayodolen.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.Adapter.RecyclerAdapterKategori;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.R;


public class HomeKategoriFragment extends Fragment {
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home_kategori, container, false);


        return v;
    }

}
