package com.example.android.ayodolen.Fragment.Tiket;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.R;

import java.text.NumberFormat;
import java.util.Locale;


public class PilihJumlahTiketFragment extends Fragment {
    View v;Bundle bndl;
    NumberFormat formatRupiah;Locale localeID;
    TextView nama,hargaawal,hargadiskon,hargatotal,totalstrike,hargapak;
    Button btnLanjut;
    ElegantNumberButton eleganNumberBut;
    Integer jumlah =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pilih_jumlah_tiket, container, false);
        bndl = getArguments();

        CariTiketActivity activitymain = (CariTiketActivity) getActivity();
        activitymain.gantiTitel("Pesan Tiket",bndl.getString("nama"));

        initLayout();
        loadData();
        initListener();
        return v;
    }

    void initLayout(){
//        init data from layout
        nama = v.findViewById(R.id.tv_namatiket);
        hargaawal = v.findViewById(R.id.tv_hargaawal);
        hargadiskon = v.findViewById(R.id.tv_hargadiskon);
        hargatotal = v.findViewById(R.id.tv_hargatotal);
        hargapak = v.findViewById(R.id.tv_hargapak);
        btnLanjut = v.findViewById(R.id.btn_lanjutkan);
        totalstrike = v.findViewById(R.id.tv_totalstrike);
        eleganNumberBut = v.findViewById(R.id.enb_jumlah);

        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    }


    void loadData(){
        nama.setText(bndl.getString("nama")+" - "+bndl.getString("jenistiket")+" - "+bndl.getString("type"));
        hargaawal.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargaAwal"))));
        hargaawal.setPaintFlags(hargaawal.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        hargadiskon.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargaDiskon"))));
        hargapak.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargaDiskon"))));

        eleganNumberBut.setNumber("1");
        hargatotal.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargaDiskon"))));
        jumlah = Integer.parseInt(bndl.getString("hargaDiskon"));
        totalstrike.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargaAwal"))));
        totalstrike.setPaintFlags(hargaawal.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
    }


    void initListener(){

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CariTiketActivity activitymain = (CariTiketActivity) getActivity();
                Bundle bun = new Bundle();
                bun.putString("id",bndl.getString("id"));
                bun.putString("nama",bndl.getString("nama"));
                bun.putString("hargasatuan",bndl.getString("hargaDiskon"));
                bun.putString("hargatotal",jumlah.toString());
                bun.putString("jumlah",eleganNumberBut.getNumber());
                bun.putString("type",bndl.getString("type"));
                bun.putString("jenistiket",bndl.getString("jenistiket"));

                PilihTanggalFragment konf= new PilihTanggalFragment();
                konf.setArguments(bun);
                activitymain.getSupportFragmentManager().beginTransaction()
                        .addToBackStack("PilihJumlah")
                        .replace(R.id.frament_container,konf).commit();
                activitymain.gantiTitel("Lengkapi Data","");
            }
        });

        eleganNumberBut.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                jumlah = Integer.parseInt(eleganNumberBut.getNumber());
                if (jumlah!=0){
                    if(newValue ==10){
                        eleganNumberBut.setNumber("9");
                        jumlah = Integer.parseInt(eleganNumberBut.getNumber());

                        final BottomSheetDialog notif = new BottomSheetDialog(getActivity());
                        notif.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        notif.setContentView(R.layout.item_maximum_pax);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notif.dismiss();
                            }
                        },1500);
                        notif.show();
                    }

                    Integer jumlahawal =  Integer.parseInt(bndl.getString("hargaAwal"))*jumlah;
                    Integer jumlahdiskon =  Integer.parseInt(bndl.getString("hargaDiskon"))*jumlah;
                    jumlah=jumlahdiskon;

                    hargatotal.setText(formatRupiah.format(jumlahdiskon));
                    totalstrike.setText(formatRupiah.format(jumlahawal));
                    totalstrike.setPaintFlags(hargaawal.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    eleganNumberBut.setNumber("1");
                    jumlah = Integer.parseInt(eleganNumberBut.getNumber());

                    Integer jumlahawal =  Integer.parseInt(bndl.getString("hargaAwal"))*jumlah;
                    Integer jumlahdiskon =  Integer.parseInt(bndl.getString("hargaDiskon"))*jumlah;
                    jumlah=jumlahdiskon;

                    hargatotal.setText(formatRupiah.format(jumlahdiskon));
                    totalstrike.setText(formatRupiah.format(jumlahawal));
                    totalstrike.setPaintFlags(hargaawal.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

    }


    @Override
    public void onStart() {
        CariTiketActivity activitymain = (CariTiketActivity) getActivity();
        activitymain.gantiTitel("Pesan Tiket",activitymain.i.getStringExtra("namawisata"));
        super.onStart();
    }
}
