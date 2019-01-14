package com.example.android.ayodolen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;
import com.example.android.ayodolen.Session.Verified;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText email, password;
    Button btnLogin;
    TextView linkDaftar;
    Verified verf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        verf = new Verified();
        //getActionBar().hide();
        email = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
//        getActionBar().hide();

        btnLogin = findViewById(R.id.buttonYuk);
//        getActionBar().hide();
        final SessionManagement s1 = new SessionManagement(this);

        if (s1.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
            finish();
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog loading=new Dialog(LoginActivity.this);
                loading.setContentView(R.layout.item_loading);
                loading.setCancelable(false);
                loading.show();

                if (verf.cekLogin(email,password)){

                    ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<UserResponse> mLogin = mApiInterface.loginRequest(email.getText().toString(),password.getText().toString());
                    mLogin.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            String status = response.body().getStatus();
                            if (status.equals("success")){
                                User user = response.body().getUser();
                                s1.createLoginSession(user.getEmail().toString(),user.getPassword().toString(), user.getId_user().toString(),true);
                                s1.createSkipSession(true);

                                finish();
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                // Closing all the Activities
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                // Add new Flag to start new Activity
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }else {
                                passSalah();
                            }
                            loading.dismiss();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            loading.dismiss();
                            onFail();
                        }
                    });
                }else loading.dismiss();

            }
        });

        linkDaftar = findViewById(R.id.linkDaftar);
        linkDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void passSalah() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.item_fail_login);
        dialog.setCancelable(false);
        Button tutup = dialog.findViewById(R.id.btn_tutup);
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void onFail() {
        if (LoginActivity.this!=null){
            Dialog dialog = new Dialog(LoginActivity.this);
            dialog.setContentView(R.layout.item_nointernet);
            dialog.setCancelable(false);
            Button tryagain = dialog.findViewById(R.id.btn_cobalagi);
            tryagain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(LoginActivity.this,SplashActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            dialog.show();
        }
    }


}
