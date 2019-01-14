package com.example.android.ayodolen;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Model.RegistrasiUser;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.Verified;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText nama, email,nohp, pwd;
    ApiInterface mApiInterface;
    Verified verfi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getActionBar().hide();


        verfi = new Verified();
        Toolbar toolbar = findViewById(R.id.toolbarRegistrasi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        nama = findViewById(R.id.inputNama);
        email = findViewById(R.id.inputemail);
        nohp = findViewById(R.id.inputnohp);
        pwd = findViewById(R.id.inputPasswd);
        register = findViewById(R.id.btnRegister);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog loading=new Dialog(RegisterActivity.this);
                loading.setContentView(R.layout.item_loading);
                loading.setCancelable(false);
                loading.show();

                if (verfi.cekDaftar(nama,email,nohp,pwd)){
                    Call<RegistrasiUser> newUser = mApiInterface.registrasiUser( email.getText().toString(),
                            pwd.getText().toString(),nohp.getText().toString(), nama.getText().toString());

                    newUser.enqueue(new Callback<RegistrasiUser>() {
                        @Override
                        public void onResponse(Call<RegistrasiUser> call, Response<RegistrasiUser> response) {
                            if(response.body().getStatus().equals("404")){
                                final Dialog dialog = new Dialog(RegisterActivity.this);
                                dialog.setContentView(R.layout.item_register_warning);
                                dialog.setCancelable(false);
                                TextView msg = dialog.findViewById(R.id.tvmessage);
                                msg.setText(response.body().getMessage());
                                Button tutup = dialog.findViewById(R.id.btn_tutup);
                                tutup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }else{
                                final Dialog dialog = new Dialog(RegisterActivity.this);
                                dialog.setContentView(R.layout.item_register_sukses);
                                dialog.setCancelable(false);
                                Button tutup = dialog.findViewById(R.id.btn_tutup);
                                tutup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                                dialog.show();
                            }
                            loading.dismiss();
                        }

                        @Override
                        public void onFailure(Call<RegistrasiUser> call, Throwable t) {
                            if (RegisterActivity.this!=null){
                                Dialog dialog = new Dialog(RegisterActivity.this);
                                dialog.setContentView(R.layout.item_nointernet);
                                dialog.setCancelable(false);
                                Button tryagain = dialog.findViewById(R.id.btn_cobalagi);
                                tryagain.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent i = new Intent(RegisterActivity.this,SplashActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                                loading.dismiss();
                                dialog.show();
                            }
                        }
                    });

                }else loading.dismiss();

            }
        });


    }


}
