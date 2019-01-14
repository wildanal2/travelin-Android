package com.example.android.ayodolen.Fragment.Bantuan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.Adapter.RecyclerAdapterBantuan;
import com.example.android.ayodolen.R;

import java.util.LinkedList;
import java.util.List;

public class BantuanHomeFragment extends Fragment {

    View v; Toolbar toolbar;
    BantuanActivity mActv;
    RecyclerView mRecyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_bantuan_home, container, false);
         mActv = (BantuanActivity) getActivity();

        initLayout();
        initListener();
        return v;
    }

    void initLayout(){
        toolbar = v.findViewById(R.id.toolbar);
        mRecyView = v.findViewById(R.id.recycler_bantuan);


        mRecyView.requestFocus();
        mActv.setSupportActionBar(toolbar);
        mActv.getSupportActionBar().setTitle("");
        if(mActv.getSupportActionBar() != null) mActv.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> mbantuan = new LinkedList<>();
        mbantuan.add("Cara Melakukan Pembayaran");

        mRecyView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerAdapterBantuan madapter = new RecyclerAdapterBantuan(mbantuan,getActivity());
        mRecyView.setAdapter(madapter);


    }

    void initListener(){

        //btnback
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActv.finish();
            }
        });


    }

}
