package com.example.android.ayodolen.Fragment.Transaksi;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.RecyclerAdapterTransaksi;
import com.example.android.ayodolen.Fragment.Bantuan.BantuanActivity;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.Model.GetPembayaran;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;
import com.example.android.ayodolen.SplashActivity;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananFragment extends Fragment {

    View v;SessionManagement ses;
    ApiInterface mApiInterface;
    SpinKitView loading;
    HomeActivity mActv;
    RecyclerView rvTransaksi;
    public List<Pembayaran> db_Pembayaran ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pesanan, container, false);

        db_Pembayaran = new ArrayList<>();
        mActv = (HomeActivity) getActivity();
        ses = new SessionManagement(getContext());
        loading = v.findViewById(R.id.spin_kit);
        initLayout();
        loadData();
        return v;
    }

    private void loadData() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetPembayaran> transaksi = mApiInterface.pembayaranbyuser(ses.getKeyId());
        transaksi.enqueue(new Callback<GetPembayaran>() {
            @Override
            public void onResponse(Call<GetPembayaran> call, Response<GetPembayaran> response) {
                loading.setVisibility(View.GONE);
                if (response.body().getStatus().equals("200")){
                    db_Pembayaran = response.body().getPembayaranList();
                    if (mActv.getSupportFragmentManager()!=null){
                        rvTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerAdapterTransaksi madapter = new RecyclerAdapterTransaksi(db_Pembayaran,getContext());
                        rvTransaksi.setAdapter(madapter);
                    }
                }else {
                    if (getContext()!=null){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_pesanan,new PesananKosongFragment()).commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPembayaran> call, Throwable t) {
                if (getContext()!=null){
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.item_nointernet);
                    dialog.setCancelable(false);
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
        Toolbar toolbar = v.findViewById(R.id.toolbarSeacrh);
        rvTransaksi = v.findViewById(R.id.rv_transaksi);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Pesanan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_pesanan,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.bantuanmenu){
            Intent i = new Intent(getActivity(),BantuanActivity.class);
            getActivity().startActivity(i);

        }if (id==R.id.pengaturanmenu){
            final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.menu_setting_pesanan);
            RadioGroup rGroup = dialog.findViewById(R.id.radio_groupberdasarkan);

            rGroup.setEnabled(false);
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


}
