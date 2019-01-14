package com.example.android.ayodolen;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Fragment.Tiket.BayarFragment;
import com.example.android.ayodolen.Fragment.Transaksi.CountdownTimeFragment;
import com.example.android.ayodolen.Fragment.Transaksi.TimeoutFragment;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Model.PembayaranResponse;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BayarActivity extends AppCompatActivity {
    Toolbar toolbar; Intent i;
    TextView tvKodeBayar,tvTotalBayar;
    Button btnBayar;
    NumberFormat formatRupiah;Locale localeID;
    public Date tglTimeout = null;
    ApiInterface mApiInterface;
    Pembayaran mTrnski;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        i = getIntent();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Travelin Bayar");
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PembayaranResponse> trans = mApiInterface.pembayaranbyid(i.getStringExtra("id"));
        trans.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                mTrnski = response.body().getmPembayaran();
                initLayout();
                initListener();
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                Toast.makeText(BayarActivity.this, "Something Worng "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initLayout() {
//        init layout
        tvKodeBayar = (TextView) findViewById(R.id.tv_kodepembayaran);
        tvTotalBayar = (TextView) findViewById(R.id.tv_totalbayar);
        btnBayar = findViewById(R.id.btn_bayar);

        try {
            tglTimeout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mTrnski.getTanggaltimeout());
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        rupiah
        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);


//        sett
        getSupportActionBar().setSubtitle("No.Transaksi "+mTrnski.getId());
        try {
            Date futureDate = tglTimeout;
            Date currentDate = new Date();

            if (!currentDate.after(futureDate)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_time,new CountdownTimeFragment())
                        .commit();
                btnBayar.setVisibility(View.VISIBLE);
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_time,new TimeoutFragment())
                        .commit();
                btnBayar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvKodeBayar.setText(mTrnski.getKodepembayaran());
        tvTotalBayar.setText(formatRupiah.format(Integer.parseInt(mTrnski.getTotalbayar())));

    }

    private void initListener() {

        //btn back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        // btn bayar
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(BayarActivity.this);
                View v = getLayoutInflater().inflate(R.layout.item_loading_bayar,null);

                ab.setView(v);
                final AlertDialog loading = ab.create();
                loading.show();

                Call<PembayaranResponse> p = mApiInterface.merubahStatusTransaksi(i.getStringExtra("id"),"2");
                p.enqueue(new Callback<PembayaranResponse>() {
                    @Override
                    public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {

                        final Dialog dialog = new Dialog(BayarActivity.this);
                        dialog.setContentView(R.layout.item_menu_sukses);
                        dialog.setCancelable(false);

                        Button btn_ya = dialog.findViewById(R.id.btn_ya);

                        btn_ya.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                                Intent i = new Intent(BayarActivity.this, HomeActivity.class);
                                // Closing all the Activities
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                // Add new Flag to start new Activity
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("sesi","pesanan");
                                startActivity(i);
                            }
                        });
                        loading.dismiss();
                        dialog.show();
                    }

                    @Override
                    public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }



}
