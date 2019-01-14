package com.example.android.ayodolen.Fragment.Tiket;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.ayodolen.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class PilihTanggalFragment extends Fragment {
    View v;Bundle bndl;
    MaterialCalendarView mCV;
    TextView tvTglShow;
    Button btnLanjut;
    String tgl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pilih_tanggal, container, false);
        bndl = getArguments();

        initLayout();
        initListener();
        return v;
    }

    void initLayout(){
        Calendar calendar = Calendar.getInstance();
        mCV = v.findViewById(R.id.calendarView);
        tvTglShow = v.findViewById(R.id.tgl_show);
        btnLanjut = v.findViewById(R.id.btn_lanjutkan);

        btnLanjut.setVisibility(View.GONE);

        mCV.state().edit().setMinimumDate(calendar.getTime())
                .commit();
        mCV.setShowOtherDates(MaterialCalendarView.SHOW_OUT_OF_RANGE);
    }

    void initListener(){
        mCV.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat targetFormat = new SimpleDateFormat("dd, MMM yyyy");
                tvTglShow.setText(""+targetFormat.format(date.getDate()));

                SimpleDateFormat targetFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                tgl = targetFormat2.format(date.getDate());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLanjut.setVisibility(View.VISIBLE);
                    }
                },500);

            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bndl.putString("tgl", tgl);

                KonfirmasiFragment konf= new KonfirmasiFragment();
                konf.setArguments(bndl);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,konf).commit();
            }
        });
    }

}
