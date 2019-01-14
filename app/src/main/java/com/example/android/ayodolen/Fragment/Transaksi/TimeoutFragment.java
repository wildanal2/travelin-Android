package com.example.android.ayodolen.Fragment.Transaksi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeoutFragment extends Fragment {
    View v;
    TextView tvTotHarga1,tvTotHarga2,tvNamaTiket,tvHargaSatuan,tvLayanan,tvBiayaLayanan,tvSubHarga,tvTanggal;
    DetailActivity mainDa;
    Pembayaran pem;
    NumberFormat formatRupiah;Locale localeID;
    Date tgl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_timeout, container, false);
         mainDa = (DetailActivity) getActivity();


         initLayout();
         loadData();
         return v;
    }


    private void initLayout() {
        tvTotHarga1 = v.findViewById(R.id.tv_totharga);
        tvNamaTiket = v.findViewById(R.id.tv_namatiket);
        tvTanggal = v.findViewById(R.id.tv_tanggal);
        tvHargaSatuan = v.findViewById(R.id.tv_hargasatuan);
        tvLayanan = v.findViewById(R.id.tv_ketlayanan);
        tvBiayaLayanan = v.findViewById(R.id.tv_hargalayanan);
        tvSubHarga = v.findViewById(R.id.tv_subtotaltiket);
        tvTotHarga2 = v.findViewById(R.id.tv_hargatotal2);

    }

    private void loadData() {
        pem = mainDa.pe;
        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        try {
            tgl = new SimpleDateFormat("yyyy-MM-dd").parse(pem.getTanggalkunjungan());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat tgltoo = new SimpleDateFormat("EEEE, dd MMMM yyyy");

        tvTotHarga1.setText(formatRupiah.format(Integer.parseInt(pem.getTotalbayar())));
        tvNamaTiket.setText(pem.getJenistiket()+" "+pem.getNamatiket());
        tvTanggal.setText(tgltoo.format(tgl));
        tvHargaSatuan.setText("Harga @"+formatRupiah.format(Integer.parseInt(pem.getHargadiskon()))+" ("+pem.getTotaltiket()+" Tiket)");
        tvLayanan.setText("Biaya layanan ("+pem.getNama_metode()+")");
        tvBiayaLayanan.setText(formatRupiah.format(Integer.parseInt(pem.getBiayaLayanan())));
        Integer sub = (Integer.parseInt(pem.getTotaltiket())*Integer.parseInt(pem.getHargadiskon()));
        tvSubHarga.setText(formatRupiah.format(sub));
        tvTotHarga2.setText(formatRupiah.format(Integer.parseInt(pem.getTotalbayar())));

    }


}
