package com.example.android.ayodolen.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ayodolen.Model.Komentar;
import com.example.android.ayodolen.R;

import java.util.List;

public class RecyclerAdapterKomentar extends RecyclerView.Adapter<RecyclerAdapterKomentar.MyViewHolder> {

    private List<Komentar> mKomentar;
    private Context mContext;

    public RecyclerAdapterKomentar(List<Komentar> mKomentar, Context mContext) {
        this.mKomentar = mKomentar;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komentar, parent, false);
        RecyclerAdapterKomentar.MyViewHolder myViewHolder = new RecyclerAdapterKomentar.MyViewHolder(mView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama.setText(mKomentar.get(position).getNama());
        holder.komentar.setText(mKomentar.get(position).getKomentar());
        holder.tanggal.setText(mKomentar.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return mKomentar.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama, komentar, tanggal;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama);
            komentar = itemView.findViewById(R.id.tv_komentar);
            tanggal = itemView.findViewById(R.id.tv_waktu);
        }
    }
}
