package com.example.android.ayodolen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.SectionsPageAdapter;
import com.example.android.ayodolen.Fragment.Transaksi.DetailActivity;
import com.example.android.ayodolen.Fragment.Wisata.DeskripsiFragment;
import com.example.android.ayodolen.Fragment.Wisata.KomentarFragment;
import com.example.android.ayodolen.Model.Tiket;
import com.example.android.ayodolen.Model.TiketResponse;
import com.example.android.ayodolen.Model.Wisata;
import com.example.android.ayodolen.Model.WisataResponse;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailWisataActivity extends AppCompatActivity implements OnMapReadyCallback {

    Intent i;
    TextView nama_wisata, alamat,lokasi, deskripsi,hargaterendah;
    ImageView img;
    FloatingActionButton btnMaps;
    Button btn_caritiket;
    String image;
    GoogleMap mMap; Locale localeID; NumberFormat formatRupiah;
    android.support.v7.widget.Toolbar toolbar;
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private ApiInterface mApiInterface;
    private Wave mWaveDrawable,mWaveDrawable1;
    public Wisata wis ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        toolbar = findViewById(R.id.toolbar);
        deskripsi = findViewById(R.id.deskripsii);
        nama_wisata = findViewById(R.id.tvDetailNama);
        alamat = findViewById(R.id.tvDetailAlamat);
        lokasi = findViewById(R.id.lokasiinfo);
        btnMaps = findViewById(R.id.fbtnMaps);
        img = findViewById(R.id.imgDetailWisata);
        hargaterendah = findViewById(R.id.tv_minharga);
        btn_caritiket = findViewById(R.id.btn_caritiket);
        i = getIntent();


        //  set load
        mWaveDrawable = new Wave();
        mWaveDrawable.setBounds(0, 0, 100, 100);
        mWaveDrawable.setColor(getResources().getColor(R.color.colorWhite));
        mWaveDrawable1 = new Wave();
        mWaveDrawable1.setBounds(0, 0, 100, 100);
        mWaveDrawable1.setColor(getResources().getColor(R.color.colorOrangee));

        btn_caritiket.setCompoundDrawables(null,mWaveDrawable,null,null);
        hargaterendah.setCompoundDrawables(null,mWaveDrawable1,null,null);



        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        SupportMapFragment mFragmap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFragmap.getMapAsync(this);





        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        loadData();


    }

    private void loadData() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<WisataResponse> getwisata = mApiInterface.getWisataby(i.getStringExtra("id_wisata"));
        getwisata.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                wis = response.body().getWisataget();

                loadLayout();
                initListener();
                Call<TiketResponse> tiketmurah = mApiInterface.tiketmurahby(i.getStringExtra("id_wisata"));
                tiketmurah.enqueue(new Callback<TiketResponse>() {
                    @Override
                    public void onResponse(Call<TiketResponse> call, Response<TiketResponse> response) {
                        Tiket tik = response.body().getTiket();
                        if (response.body().getStatus().equals("200")){

                            btn_caritiket.setCompoundDrawables(null,null,null,null);
                            btn_caritiket.setText("Cari Tiket");

                            hargaterendah.setCompoundDrawables(null,null,null,null);
                            hargaterendah.setText(formatRupiah.format(Integer.parseInt(tik.getHargadiskon())));
                        }else {
                            hargaterendah.setCompoundDrawables(null,null,null,null);
                            hargaterendah.setText(response.body().getMessage());

                            btn_caritiket.setCompoundDrawables(null,null,null,null);
                            btn_caritiket.setText("  Tiket kosong  ");
                            btn_caritiket.setEnabled(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<TiketResponse> call, Throwable t) {
                        Log.e("Retrofit get",t.getMessage() );
                    }
                });
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {

            }
        });


    }

    private void loadLayout() {
        // set to layout
        setupViewPager(mViewPager);
        image    = i.getStringExtra("image");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(wis.getNama_wisata());
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        deskripsi.setText(wis.getDeskripsi());
        nama_wisata.setText(wis.getNama_wisata());
        alamat.setText("Lokasi : "+wis.getAlamat());
        lokasi.setText("Lokasi : "+wis.getAlamat());
//        deskripsi.setText(dsk);
        Picasso.with(getApplicationContext()).load(ApiClient.BASE_URL+"assets/image/"+image).into(img);
    }


    private void initListener() {

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maps = new Intent(getApplicationContext(),MapsActivity.class);
                maps.putExtra("lat",wis.getLatitude());
                maps.putExtra("longi",wis.getLongitude());

                startActivity(maps);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_caritiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CariTiketActivity.class);
                i.putExtra("id_wisata",wis.getId_wisata());
                i.putExtra("namawisata",wis.getNama_wisata());
                startActivity(i);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(DeskripsiFragment.newInstance(), "Gallery");
        adapter.addFragment(KomentarFragment.newInstance(i.getStringExtra("id_wisata")), "Ulasan");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng lokasi = new LatLng(i.getDoubleExtra("lt",0),i.getDoubleExtra("lng",0));
        mMap.addMarker(new MarkerOptions().position(lokasi).title("tujuan"));
        CameraPosition camLok = CameraPosition.builder().target(lokasi).zoom(10).bearing(0).tilt(45).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camLok));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailwisata,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.sharedsmenu){
            final BottomSheetDialog dialog = new BottomSheetDialog(DetailWisataActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.menu_share);

            dialog.show();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveDrawable.start();
        mWaveDrawable1.start();
    }
}
