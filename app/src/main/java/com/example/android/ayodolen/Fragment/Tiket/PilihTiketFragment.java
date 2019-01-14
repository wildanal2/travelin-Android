package com.example.android.ayodolen.Fragment.Tiket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.RecyclerAdapterPilihTiket;
import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.Model.GetTiket;
import com.example.android.ayodolen.Model.Tiket;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PilihTiketFragment extends Fragment {
    View v;Bundle bndl;
    RecyclerView rvTiket;
    private ApiInterface mApiInterface;
    List<Tiket> db_tiket = new ArrayList<>();
    CariTiketActivity activitymain ;
    SpinKitView loadingg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pilih_tiket, container, false);
        bndl = getArguments();
        activitymain = (CariTiketActivity) getActivity();

        loadingg = v.findViewById(R.id.spin_kit);
        init_layout();
        load_data();
        return v;
    }

    void init_layout(){
        rvTiket = v.findViewById(R.id.rv_pilihtiket);

    }

    void load_data(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetTiket> getTiket = mApiInterface.tiketbyid(activitymain.i.getStringExtra("id_wisata"));
        getTiket.enqueue(new Callback<GetTiket>() {
            @Override
            public void onResponse(Call<GetTiket> call, Response<GetTiket> response) {
                db_tiket = response.body().getTiketList();

                rvTiket.setLayoutManager(new LinearLayoutManager(getContext()));
                RecyclerAdapterPilihTiket madapter = new RecyclerAdapterPilihTiket(db_tiket,getContext(),getActivity());
                rvTiket.setAdapter(madapter);
                loadingg.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetTiket> call, Throwable t) {
                loadingg.setVisibility(View.GONE);
                if (getContext()!=null){
                    Toast.makeText(getContext(), "Pastikan anda menggunakan jaringan yang stabil", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void onStart() {
        activitymain.gantiTitel("Pilih Tiket",activitymain.i.getStringExtra("namawisata"));
        super.onStart();
    }
}
