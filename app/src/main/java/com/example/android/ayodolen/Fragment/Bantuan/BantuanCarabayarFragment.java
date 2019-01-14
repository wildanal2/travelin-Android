package com.example.android.ayodolen.Fragment.Bantuan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.R;


public class BantuanCarabayarFragment extends Fragment {
    View v; Toolbar toolbar;
    BantuanActivity mActv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bantuan_carabayar, container, false);
        mActv = (BantuanActivity) getActivity();

        initLayout();
        initListener();
        return v;
    }

    private void initLayout() {
        toolbar = v.findViewById(R.id.toolbar);

        mActv.setSupportActionBar(toolbar);

        if(mActv.getSupportActionBar() != null) mActv.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListener() {

        //btnback
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


    }


}
