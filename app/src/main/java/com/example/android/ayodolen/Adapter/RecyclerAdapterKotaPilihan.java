package com.example.android.ayodolen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ayodolen.DetailWisataActivity;
import com.example.android.ayodolen.Model.KotaRekom;
import com.example.android.ayodolen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterKotaPilihan extends RecyclerView.Adapter<RecyclerAdapterKotaPilihan.MyViewholder> {

    private List<KotaRekom> mKotaRekom;
    private Context mContext;

    public RecyclerAdapterKotaPilihan(List<KotaRekom> mKotaRekom, Context mContext) {
        this.mKotaRekom = mKotaRekom;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerAdapterKotaPilihan.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kota_pilihan, parent, false);
        RecyclerAdapterKotaPilihan. MyViewholder myViewHolder = new RecyclerAdapterKotaPilihan.MyViewholder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterKotaPilihan.MyViewholder holder, final int position) {
        final KotaRekom kr =mKotaRekom.get(position);

        holder.namakota.setText(kr.getNama());
        Picasso.with(mContext).load(kr.getImg()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(mContext,DetailWisataActivity.class);
                detail.putExtra("id_wisata", kr.getId());
                detail.putExtra("lt",kr.getLt());
                detail.putExtra("lng",kr.getLog());
                detail.putExtra("image",kr.getImg_source());
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mKotaRekom.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView namakota;

        public MyViewholder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgGridWisata);
            namakota = itemView.findViewById(R.id.tvGridWisata);
        }
    }
}
