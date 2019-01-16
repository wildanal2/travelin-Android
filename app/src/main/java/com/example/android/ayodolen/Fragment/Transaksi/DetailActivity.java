package com.example.android.ayodolen.Fragment.Transaksi;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.BayarActivity;
import com.example.android.ayodolen.Fragment.Bantuan.BantuanActivity;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Model.PembayaranResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.github.ybq.android.spinkit.style.Wave;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    Intent i;ApiInterface mApi;
    Button btnBayar;
    TextView tvStatus; ImageView img_status;
    public Pembayaran pe;
    Toolbar toolbar;
    private Wave mWaveDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        i = getIntent();

        mApi = ApiClient.getClient().create(ApiInterface.class);
        toolbar = findViewById(R.id.toolbar);
        tvStatus = findViewById(R.id.tv_status);
        btnBayar = findViewById(R.id.btn_bayar);
        img_status = findViewById(R.id.img_status);
        mWaveDrawable = new Wave();
        mWaveDrawable.setBounds(0, 0, 80, 80);
        mWaveDrawable.setColor(getResources().getColor(R.color.colorPrimary));
        tvStatus.setCompoundDrawables(null,mWaveDrawable,null,null);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData();

        initListener();
    }


    private void loadData() {
        final Dialog loadd = new Dialog(DetailActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        loadd.setContentView(R.layout.item_loading_null);
        loadd.setCancelable(false);
        loadd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadd.show();

        Call<PembayaranResponse> p = mApi.pembayaranbyid(i.getStringExtra("id"));
        p.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                pe = response.body().getmPembayaran();

                if(pe.getStatus().equals("2")){
                    tvStatus.setText(R.string.trnsk_sukses);
                    img_status.setImageResource(R.drawable.logo_kasir_happy);
                    btnBayar.setEnabled(false);
                    btnBayar.setVisibility(View.GONE);
                    if (getApplicationContext()!=null) getSupportFragmentManager().beginTransaction().replace(R.id.container_detail, new DetailTiketFragment()).commit();

                }else {
                    if(pe.getStatus().equals("0")){
                        img_status.setImageResource(R.drawable.logo_kasir_sad);
                        tvStatus.setText(R.string.trnsk_batal);
                        btnBayar.setEnabled(false);
                    }else if(pe.getStatus().equals("1")) {
                        img_status.setImageResource(R.drawable.logo_kasir);
                        tvStatus.setText(R.string.trnsk_menunggu);
                        btnBayar.setVisibility(View.VISIBLE);
                    }
                    if (getApplicationContext()!=null) getSupportFragmentManager().beginTransaction().replace(R.id.container_detail, new TimeoutFragment()).commit();
                }
                tvStatus.setCompoundDrawables(null,null,null,null);
                loadd.dismiss();
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                if (getApplicationContext()!=null) Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show(); loadd.dismiss();
            }
        });
    }

    private void initListener() {
        //btn bayar
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext(),BayarActivity.class);
                b.putExtra("id",i.getStringExtra("id"));
                startActivity(b);
            }
        });

        //btn back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_tiket,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.bantuanmenu){
            Intent i = new Intent(DetailActivity.this,BantuanActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        loadData();
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mWaveDrawable.start();
    }


}
