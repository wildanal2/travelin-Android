package com.example.android.ayodolen.Fragment.Tiket;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class KonfirmasiFragment extends Fragment {
    View v;Bundle bndl;
    NumberFormat formatRupiah;Locale localeID;SimpleDateFormat targetFormatDate,getFormatDate;
    TextView tvNamaTiket1,tvNamaTiket2,tvType,tvTanggal,tvHargaSatuan,tvjumlahTiket,tvSubTotaltiket,tvHargaKeseluruan,tvperson;
    Button btn_Lanjut;Date getDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_konfirmasi, container, false);
        bndl = getArguments();

        initDataLayout();
        initListener();
        return v;
    }

    void initDataLayout() {
        tvNamaTiket1 = v.findViewById(R.id.tv_namatiket1);
        tvType = v.findViewById(R.id.tv_type);
        tvTanggal = v.findViewById(R.id.tv_tanggaltiket);
        tvNamaTiket2 = v.findViewById(R.id.tv_namatiket2);
        tvHargaSatuan = v.findViewById(R.id.tv_hargasatuan);
        tvjumlahTiket = v.findViewById(R.id.tv_jumlahtiket);
        tvSubTotaltiket = v.findViewById(R.id.tv_subtotaltiket);
        tvHargaKeseluruan = v.findViewById(R.id.tv_hargakeseluruhan);
        tvperson = v.findViewById(R.id.tv_person);
        btn_Lanjut = v.findViewById(R.id.btn_lanjutkan);

        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        getFormatDate = new SimpleDateFormat("yyyy-MM-dd");
        targetFormatDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");

        try {
            getDate = getFormatDate.parse(bndl.getString("tgl"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvNamaTiket1.setText(bndl.getString("nama")+" - "+bndl.getString("jenistiket"));
        tvType.setText(bndl.getString("type"));
        tvperson.setText(bndl.getString("jumlah")+" Orang");
        tvTanggal.setText(""+targetFormatDate.format(getDate));
        tvNamaTiket2.setText(bndl.getString("nama")+" - "+bndl.getString("jenistiket"));
        tvHargaSatuan.setText("@"+formatRupiah.format(Integer.parseInt(bndl.getString("hargasatuan"))));
        tvjumlahTiket.setText("Harga ("+bndl.getString("jumlah")+" Tickets)");
        tvSubTotaltiket.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargatotal"))));
        tvHargaKeseluruan.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargatotal"))));

    }

    void initListener(){

        btn_Lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.item_menu_konfirmasilanjutan);

                Button btn_batal = dialog.findViewById(R.id.btn_batal);
                Button btn_ya = dialog.findViewById(R.id.btn_ya);

                btn_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btn_ya.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        BayarFragment bayFrag = new BayarFragment();
                        bayFrag.setArguments(bndl);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,bayFrag)
                                .addToBackStack(null).commit();
                    }
                });

                dialog.show();


            }
        });

    }


    @Override
    public void onStart() {
        CariTiketActivity activitymain = (CariTiketActivity) getActivity();
        activitymain.gantiTitel("Lengkapi Data","");
        super.onStart();
    }
}
