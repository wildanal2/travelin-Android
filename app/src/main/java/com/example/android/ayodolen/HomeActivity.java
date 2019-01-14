package com.example.android.ayodolen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.ayodolen.Adapter.BottomNavigationViewHelper;
import com.example.android.ayodolen.Fragment.HomeFragment;
import com.example.android.ayodolen.Fragment.PleaseLoginFragment;
import com.example.android.ayodolen.Fragment.Transaksi.PesananFragment;
import com.example.android.ayodolen.Fragment.ProfileFragment;
import com.example.android.ayodolen.Fragment.SearchFragment;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Session.SessionManagement;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public List<Kategori> mKategori = new ArrayList<>();
    SessionManagement ses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ses = new SessionManagement(this);
        Intent i = getIntent();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_home);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        BottomNavigationViewHelper.removeShiftMode(bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    Bundle bndl = new Bundle();

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_pemesanan:
                            bndl.putString("titel","Pesanan");
                            if (ses.isLoggedIn()){
                                selectedFragment = new PesananFragment();
                            }else {
                                selectedFragment = new PleaseLoginFragment();
                                selectedFragment.setArguments(bndl);
                            }
                            break;
                        case R.id.nav_profile:
                            bndl.putString("titel","Akun Saya");
                            if (ses.isLoggedIn()){
                                selectedFragment = new ProfileFragment();
                            }else {
                                selectedFragment = new PleaseLoginFragment();
                                selectedFragment.setArguments(bndl);
                            }
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda yakin ingin keluar dari aplikasi ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }



}
