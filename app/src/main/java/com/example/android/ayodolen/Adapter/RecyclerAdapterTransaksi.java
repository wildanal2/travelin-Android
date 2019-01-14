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
import android.widget.Toast;

import com.example.android.ayodolen.Fragment.Transaksi.CountdownTimeFragment;
import com.example.android.ayodolen.Fragment.Transaksi.DetailActivity;
import com.example.android.ayodolen.KategoriActivity;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Model.PembayaranResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterTransaksi extends RecyclerView.Adapter<RecyclerAdapterTransaksi.MyViewholder> {

    private List<Pembayaran> mPembayaran;
    private Context mContext;

    public RecyclerAdapterTransaksi(List<Pembayaran> mPembayaran, Context mContext) {
        this.mPembayaran = mPembayaran;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerAdapterTransaksi.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        RecyclerAdapterTransaksi. MyViewholder myViewHolder = new RecyclerAdapterTransaksi.MyViewholder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterTransaksi.MyViewholder holder, final int position) {
        final Pembayaran pem = mPembayaran.get(position);

        holder.notransaksi.setText("No.Transaksi "+pem.getId());
        Date tgl = null,tgltimeout=null;
        try {
            tgl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pem.getTanggalpesan());
            tgltimeout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pem.getTanggaltimeout());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");

        holder.tanggal.setText(" "+targetFormat.format(tgl));
        holder.nama.setText(pem.getJenistiket()+"-"+pem.getNamatiket());
//        holder.nama.setText(pem.getId_tiket());

        //ket
        Date currentDate = new Date();
        if (currentDate.after(tgltimeout)) {

            //merubah data database jika sakarang melewati timeout
            if (pem.getStatus().equals("1")){
                ApiInterface mApi = ApiClient.getClient().create(ApiInterface.class);
                Call<PembayaranResponse> p = mApi.merubahStatusTransaksi(pem.getId(),"0");
                p.enqueue(new Callback<PembayaranResponse>() {
                    @Override
                    public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                        Toast.makeText(mContext, pem.getNamatiket()+" Transaksi dibatalkan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                        Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                holder.status.setText(R.string.trnsk_batal);
            }else if (pem.getStatus().equals("2")){
                    holder.status.setText(R.string.trnsk_sukses);
            }else {
                    holder.status.setText(R.string.trnsk_batal);
            }

        }else {
            if (pem.getStatus().equals("1")){
                holder.status.setText(R.string.trnsk_menunggu);
            }else if (pem.getStatus().equals("2")){
                holder.status.setText(R.string.trnsk_sukses);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(mContext,DetailActivity.class);
                b.putExtra("id",pem.getId());
                b.putExtra("status",holder.status.getText());
                mContext.startActivity(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPembayaran.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView notransaksi,tanggal,nama,status;

        public MyViewholder(View itemView) {
            super(itemView);
            notransaksi = itemView.findViewById(R.id.tv_notransaksi);
            tanggal = itemView.findViewById(R.id.tv_tanggal);
            nama = itemView.findViewById(R.id.tv_nama);
            status = itemView.findViewById(R.id.tv_status);
        }
    }
}
