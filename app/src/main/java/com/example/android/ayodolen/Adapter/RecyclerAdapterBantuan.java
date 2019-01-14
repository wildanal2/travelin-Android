package com.example.android.ayodolen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ayodolen.Fragment.Bantuan.BantuanActivity;
import com.example.android.ayodolen.Fragment.Bantuan.BantuanCarabayarFragment;
import com.example.android.ayodolen.Fragment.Bantuan.BantuanHomeFragment;
import com.example.android.ayodolen.KategoriActivity;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterBantuan extends RecyclerView.Adapter<RecyclerAdapterBantuan.MyViewholder> {

    private List<String> mBantuan;
    private FragmentActivity mActv;

    public RecyclerAdapterBantuan(List<String> mBantuan, FragmentActivity mActv) {
        this.mBantuan = mBantuan;
        this.mActv = mActv;
    }

    @NonNull
    @Override
    public RecyclerAdapterBantuan.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bantuan, parent, false);
        RecyclerAdapterBantuan. MyViewholder myViewHolder = new RecyclerAdapterBantuan.MyViewholder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterBantuan.MyViewholder holder, final int position) {
        holder.namaBantuan.setText(mBantuan.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BantuanActivity mact = (BantuanActivity) mActv ;
                mact.getSupportFragmentManager().beginTransaction().replace(R.id.container_main_bantuan, new BantuanCarabayarFragment())
                        .addToBackStack(null).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mBantuan.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView namaBantuan;

        public MyViewholder(View itemView) {
            super(itemView);
            namaBantuan = itemView.findViewById(R.id.tv_namabantuan);
        }
    }
}
