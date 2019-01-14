package com.example.android.ayodolen.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.ayodolen.Adapter.RecyclerAdapterWisata;
import com.example.android.ayodolen.Model.GetWisata;
import com.example.android.ayodolen.Model.Wisata;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.SplashActivity;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 27/11/2018.
 */

public class SearchFragment extends Fragment {

    private ApiInterface mApiInterface;
    private MaterialSearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterWisata  mAdapter;
    private List<Wisata> mWisata = new ArrayList<>();
    SpinKitView load;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);

        Toolbar toolbar = view.findViewById(R.id.toolbarSeacrh);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Cari Wisata");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

        mRecyclerView = view.findViewById(R.id.rv_search);
        searchView = view.findViewById(R.id.search_view);
        load = view.findViewById(R.id.spin_kit);
        loadData();

        return view;
    }

    private void initListener() {
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    public void loadData(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetWisata> getWisata = mApiInterface.getWisata();
        getWisata.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                mWisata = response.body().getWisataList();

                load.setVisibility(View.GONE);
                mAdapter = new RecyclerAdapterWisata(mWisata,getContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
                initListener();
            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                load.setVisibility(View.GONE);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
    }
}
