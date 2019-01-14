package com.example.android.ayodolen.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.LoginActivity;
import com.example.android.ayodolen.Model.Tiket;
import com.example.android.ayodolen.Fragment.Tiket.PilihJumlahTiketFragment;
import com.example.android.ayodolen.Fragment.Tiket.PilihTiketFragment;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Session.SessionManagement;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapterPilihTiket extends RecyclerView.Adapter<RecyclerAdapterPilihTiket.MyViewHolder> {

    List<Tiket> mTiket;
    Context mContex;
    FragmentActivity mActv;

    public RecyclerAdapterPilihTiket(List<Tiket> mTiket,Context mContex,FragmentActivity mActv) {
        this.mTiket = mTiket;
        this.mContex = mContex;
        this.mActv = mActv;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tiket, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Tiket t = mTiket.get(position);

        holder.nama.setText(t.getNamatiket()+" - "+t.getJenistiket()+" - "+t.getKelas());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.hargaAwal.setText(formatRupiah.format(Integer.parseInt(t.getHargaawal())));
        holder.hargaAwal.setPaintFlags(holder.hargaAwal.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);

        holder.hargaDiskon.setText(formatRupiah.format(Integer.parseInt(t.getHargadiskon())));

        holder.btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement ses = new SessionManagement(mContex);
                if (ses.isLoggedIn()){
                    CariTiketActivity activitymain = (CariTiketActivity) mActv;
                    Bundle bun = new Bundle();
                    bun.putString("id",t.getId());
                    bun.putString("nama",t.getNamatiket());
                    bun.putString("jenistiket",t.getJenistiket());
                    bun.putString("hargaAwal",t.getHargaawal());
                    bun.putString("hargaDiskon",t.getHargadiskon());
                    bun.putString("type",t.getKelas());

                    PilihJumlahTiketFragment pilJum= new PilihJumlahTiketFragment();
                    pilJum.setArguments(bun);
                    activitymain.getSupportFragmentManager().beginTransaction()
                            .add(new PilihTiketFragment(),"PilihTiket")
                            .addToBackStack("PilihTiket")
                            .replace(R.id.frament_container,pilJum).commit();
                }else {
                    final Dialog dialo = new Dialog(mContex);
                    dialo.setContentView(R.layout.item_please_login);
                    Button btn = dialo.findViewById(R.id.btn_ya);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(mActv,LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mActv.startActivity(i);
                            dialo.dismiss();
                        }
                    });
                    dialo.show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mTiket.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama,hargaAwal,hargaDiskon;
        Button btnPilih;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_namatiket);
            hargaAwal = itemView.findViewById(R.id.tv_hargaawal);
            hargaDiskon = itemView.findViewById(R.id.tv_hargadiskon);
            btnPilih = itemView.findViewById(R.id.btn_pilih);

        }
    }
}
