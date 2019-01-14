package com.example.android.ayodolen.Fragment.Bantuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.ayodolen.R;

public class BantuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_main_bantuan, new BantuanHomeFragment()).commit();
    }
}
