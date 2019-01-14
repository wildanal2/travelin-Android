package com.example.android.ayodolen.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Fragment.Bantuan.BantuanActivity;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.LoginActivity;
import com.example.android.ayodolen.MainActivity;
import com.example.android.ayodolen.Model.DetailTransaksi;
import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;
import com.example.android.ayodolen.SplashActivity;
import com.github.ybq.android.spinkit.style.Wave;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 27/11/2018.
 */

public class ProfileFragment extends Fragment {

    View v; User user; DetailTransaksi detTrans;
    TextView tvuname,tvnama,tvemail,tvnohp;
    TextView tvTotTrans,tvBerhasil,tvWaiting,tvBatal;
    RoundKornerLinearLayout btnBantuan;
    SessionManagement s1;
    private Wave mWaveDrawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Akun Saya");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

        tvuname = v.findViewById(R.id.tv_uname);
        tvnama = v.findViewById(R.id.tv_nama);
        tvemail = v.findViewById(R.id.tv_email);
        tvnohp = v.findViewById(R.id.tv_nohp);
        tvTotTrans = v.findViewById(R.id.tvtottrans);
        tvBerhasil = v.findViewById(R.id.tv_totberhasil);
        tvWaiting = v.findViewById(R.id.tv_waiting);
        tvBatal = v.findViewById(R.id.tv_totbatal);
        btnBantuan = v.findViewById(R.id.btn_bantuan);
        mWaveDrawable = new Wave();
        mWaveDrawable.setBounds(0, 0, 100, 100);
        mWaveDrawable.setColor(getResources().getColor(R.color.colorPrimary));

        tvuname.setCompoundDrawables(null,mWaveDrawable,null,null);

//        tvuname.setCompoundDrawables(null,mWaveDrawable,null,null);
//        tvuname.setCompoundDrawables(null,mWaveDrawable,null,null);
        s1 = new SessionManagement(getContext());
        loadData(s1);
        initListener();

        return v;

    }



    private void loadData(final SessionManagement s1){
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> mLogin = mApiInterface.getdetailuser(s1.getKeyId());
        mLogin.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String status = response.body().getStatus();
                if (status.equals("success")){
                    user = response.body().getUser();
                    detTrans = response.body().getDetail();


                    initLayout();
                }else {
                    Toast.makeText(getContext(),"fail load data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if (getContext()!=null){
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.item_nointernet);
                    Button tryagain = dialog.findViewById(R.id.btn_cobalagi);
                    tryagain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(getContext(),SplashActivity.class);
                            getActivity().startActivity(i);
                            getActivity().finish();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private void initLayout() {
        tvuname.setCompoundDrawables(null,null,null,null);

        tvuname.setText(user.getNama());
        tvnama.setText(user.getNama());
        tvemail.setText(user.getEmail());
        tvnohp.setText(user.getNohp());
        //
        tvTotTrans.setText(detTrans.getTotal());
        tvBerhasil.setText(detTrans.getSukses());
        tvWaiting.setText(detTrans.getMenunggu());
        tvBatal.setText(detTrans.getBatal());
    }

    private void initListener() {
        btnBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),BantuanActivity.class);
                getActivity().startActivity(i);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_profil,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.bantuanmenu){
            Toast.makeText(getContext(),"bantuan menu",Toast.LENGTH_SHORT).show();
        }if (id==R.id.logout){
            final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.item_menu_logout);

            TextView tvLog = dialog.findViewById(R.id.tv_logout);
            Button logout = dialog.findViewById(R.id.btn_logout);
            Button batal = dialog.findViewById(R.id.btn_batal);
            batal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            if (user!=null){
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        s1.logoutUser();
                        getActivity().finish();
                        Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        // Closing all the Activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // Add new Flag to start new Activity
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                });
                tvLog.setText("Apa anda ingin keluar sebagai "+user.getNama()+" ?");

            }else {
                tvLog.setText("Pastikan anda terhubung Internet");
            }

            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        mWaveDrawable.start();
    }

}
