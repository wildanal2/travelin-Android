package com.example.android.ayodolen.Fragment.Transaksi;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ayodolen.BayarActivity;
import com.example.android.ayodolen.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownTimeFragment extends Fragment {
    View v;
    TextView txtTimerHour, txtTimerMinute, txtTimerSecond,tvKetTimeout;
    private Handler handler; private Runnable runnable;
    BayarActivity mainAct;
    SimpleDateFormat targetFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_countdown_time, container, false);
        mainAct = (BayarActivity) getActivity();


        txtTimerHour = (TextView) v.findViewById(R.id.tv_jam);
        txtTimerMinute = (TextView) v.findViewById(R.id.tv_minute);
        txtTimerSecond = (TextView) v.findViewById(R.id.tv_second);
        tvKetTimeout = (TextView) v.findViewById(R.id.tv_kettimeout);


        targetFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy, HH:mm");
        countDownStart(mainAct.tglTimeout);
        tvKetTimeout.setText("(Sebelum "+targetFormat.format(mainAct.tglTimeout)+" WIB)");
        return v;
    }

    private void countDownStart(final Date time) {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {

                    Date futureDate = time;
                    Date currentDate = new Date();

                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
//                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(": " + String.format("%02d", minutes));
                        txtTimerSecond.setText(": " + String.format("%02d", seconds));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);

    }

}
