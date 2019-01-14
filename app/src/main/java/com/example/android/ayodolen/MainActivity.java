package com.example.android.ayodolen;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.android.ayodolen.Session.SessionManagement;

public class MainActivity extends AppCompatActivity {

    private Button mButtonMasuk;
    private TextView btnLewati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mButtonMasuk = (Button) findViewById(R.id.buttonMasuk);
        btnLewati = findViewById(R.id.lewati);
        final SessionManagement ses = new SessionManagement(this);



        //click
        mButtonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        btnLewati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ses.createSkipSession(true);
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                i.putExtra("sesi","home");
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda yakin ingin keluar dari aplikasi ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

}
