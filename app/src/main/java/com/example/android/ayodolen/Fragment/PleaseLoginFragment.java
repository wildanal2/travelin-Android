package com.example.android.ayodolen.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ayodolen.LoginActivity;
import com.example.android.ayodolen.R;


public class PleaseLoginFragment extends Fragment {
    View v;
    Bundle bndl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_please_login, container, false);
        bndl = getArguments();

        initLayout();
        return v;
    }

    private void initLayout() {
        Toolbar toolbar = v.findViewById(R.id.toolbarSeacrh);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(""+bndl.getString("titel"));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

        TextView btnLogin = v.findViewById(R.id.btn_masuk);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(i);
            }
        });

    }

}
