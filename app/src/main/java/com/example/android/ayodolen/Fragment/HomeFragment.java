package com.example.android.ayodolen.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.RecyclerAdapterKategori;
import com.example.android.ayodolen.Adapter.RecyclerAdapterKotaPilihan;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.KategoriActivity;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.Model.KategoriResponse;
import com.example.android.ayodolen.Model.KotaRekom;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.SplashActivity;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

/**
 * Created by user on 27/11/2018.
 */

public class HomeFragment extends Fragment {
    FlipperLayout flipper;
    private ApiInterface mApiInterface;
    private RecyclerView rvKotapilihan;
    public List<Kategori> mKategori = new ArrayList<>();
    AdView mAdview; SpinKitView loading;
    HomeActivity mActv;
    View view;
    private RecyclerView rvKategori; private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        flipper = view.findViewById(R.id.flipper);
        mActv = (HomeActivity) getActivity();

        rvKategori = view.findViewById(R.id.rv_kategori);
        loading = view.findViewById(R.id.spin_kit);
        rvKotapilihan = view.findViewById(R.id.rv_rekomantibosan);

        loadKategori();
        loadDataKotaPilihan();

        setLayout();

        return view;
    }

    private void loadKategori(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<KategoriResponse> getKategori = mApiInterface.getKategori();
        getKategori.enqueue(new Callback<KategoriResponse>() {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response) {
                mKategori = response.body().getKategoriList();
                mActv.mKategori = mKategori;

                loading.setVisibility(View.GONE);
                if (mKategori!=null){
                    rvKategori.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    mAdapter = new RecyclerAdapterKategori(mActv.mKategori, getActivity());
                    rvKategori.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable t) {
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

    private void setLayout(){
        String url [] = new String[]{
                "http://macigo.com/wp-content/uploads/2017/06/Museum_Angkut_3.jpg",
                "http://macigo.com/wp-content/uploads/2017/06/Pantai_Kondang_Merak_Macigo_1.jpg",
                "http://macigo.com/wp-content/uploads/2017/06/Coban_Rondo_Macigo_1.jpg"

        };

        for(int i=0; i<3; i++){
            FlipperView fv = new FlipperView(getActivity().getBaseContext());
            fv.setImageUrl(url[i]);
            flipper.addFlipperView(fv);
        }
    }

    void loadDataKotaPilihan(){
        ArrayList<KotaRekom> mkota = new ArrayList<>();
        mkota.add(new KotaRekom("25","Malang","http://4.bp.blogspot.com/-RyTOuJyepgo/VkXM7FFfmlI/AAAAAAAAAUM/ttUTeDQWnHc/s1600/Tempat%2BWisata%2Bdi%2BJawa%2BTimur.jpg",-7.942494,112.953011,"1456px-Mount_Bromo_panorama.jpg"));
        mkota.add(new KotaRekom("26","Bali","https://e3.365dm.com/18/04/1096x616/skynews-bali-file-picture_4291521.jpg?20180424141409",-8.275177,115.1668487,"danu-bratan.jpg"));
        mkota.add(new KotaRekom("27","Raja Ampat","https://pbs.twimg.com/media/DL0w2ikU8AALGJw.jpg",-0.9727297,130.0513959,"pianemo-island-raja-ampat-06294.jpg"));
        mkota.add(new KotaRekom("28","Nusa Dua","https://www.thebali.co.id/wp-content/uploads/2017/12/nusa-dua-beach-1.jpg",-8.7958519,115.2315984,"nu.jpg"));
//        mkota.add(new KotaRekom("4","Bandung","https://metaonline.id/wp-content/uploads/2017/11/Kawah-Putih-Bandung.jpg"));

        rvKotapilihan.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        RecyclerAdapterKotaPilihan mAdapterKotPil = new RecyclerAdapterKotaPilihan(mkota,getContext());
        rvKotapilihan.setAdapter(mAdapterKotPil);
    }

    @Override
    public void onResume() {
        loadKategori();
        super.onResume();
    }
}
