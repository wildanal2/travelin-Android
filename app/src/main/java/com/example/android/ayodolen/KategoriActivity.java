package com.example.android.ayodolen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.RecyclerAdapterKelompokWisata;
import com.example.android.ayodolen.Adapter.RecyclerAdapterWisata;
import com.example.android.ayodolen.Model.GetWisata;
import com.example.android.ayodolen.Model.Wisata;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Wisata> mWisata = new ArrayList<>();
    private String id_kategori;
    SpinKitView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        Intent i = getIntent();
        id_kategori = i.getStringExtra("id_kategori");

        Toolbar toolbar = findViewById(R.id.toolbarKategori);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(i.getStringExtra("kategori"));

        load = findViewById(R.id.spin_kit);
        mRecyclerView = findViewById(R.id.rv_wisata);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        loadData();
//        mAdapter = new RecyclerAdapterWisata(wisata,this);
//        mRecyclerView.setAdapter(mAdapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


    public void loadData(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetWisata> getKategr = mApiInterface.getWisataKategori(id_kategori);
        getKategr.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                mWisata = response.body().getWisataList();

                mAdapter = new RecyclerAdapterKelompokWisata(mWisata, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
                load.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(KategoriActivity.this, "PAstikan anda menggunakan koneksi yang stabil", Toast.LENGTH_SHORT).show();
                load.setVisibility(View.GONE);
            }
        });


    }



}
