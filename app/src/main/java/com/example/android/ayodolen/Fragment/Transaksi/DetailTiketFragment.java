package com.example.android.ayodolen.Fragment.Transaksi;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DetailTiketFragment extends Fragment {
    View v;
    ImageView imgQr;TextView tv_namatiket,tanggal,idTiket,person;
    DetailActivity mainAct;
    Date tgl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_detail_tiket, container, false);
         mainAct = (DetailActivity) getActivity();

         imgQr = v.findViewById(R.id.img_qr);
         tv_namatiket = v.findViewById(R.id.tv_namatiket1);
         tanggal = v.findViewById(R.id.tv_tanggaltiket);
         idTiket = v.findViewById(R.id.id_tiket);
         person = v.findViewById(R.id.tv_person);

        try {
            tgl = new SimpleDateFormat("yyyy-MM-dd").parse(mainAct.pe.getTanggalkunjungan());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        tv_namatiket.setText(mainAct.pe.getJenistiket()+" - "+mainAct.pe.getNamatiket());
        tanggal.setText(""+targetFormat.format(tgl));
        idTiket.setText("No.Tiket "+mainAct.pe.getId());
        person.setText(" "+mainAct.pe.getTotaltiket()+" Orang");

        MultiFormatWriter mulForWrit = new MultiFormatWriter();
        try {
            BitMatrix bitM =mulForWrit.encode(mainAct.pe.getKodetiket(),BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barEncode = new BarcodeEncoder();
            Bitmap bitMap = barEncode.createBitmap(bitM);
            imgQr.setImageBitmap(bitMap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        return v;
    }

}
