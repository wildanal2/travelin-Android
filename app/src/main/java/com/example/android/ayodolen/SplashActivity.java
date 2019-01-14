package com.example.android.ayodolen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.android.ayodolen.Session.SessionManagement;

/**
 * Created by kris on 13/11/18.
 */

public class SplashActivity extends AppCompatActivity {

    private static int LamaTampilSplash = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);
        final SessionManagement ses = new SessionManagement(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ses.isSkiped()){
                    goToActivity();
                }else {
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    i.putExtra("sesi","home");
                    startActivity(i);
                    finish();
                }
            }
        },LamaTampilSplash);



    };

    private void goToActivity(){
        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(i);
        finish();
    }

}
