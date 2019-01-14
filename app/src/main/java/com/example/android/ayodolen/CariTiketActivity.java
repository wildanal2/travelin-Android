package com.example.android.ayodolen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.android.ayodolen.Fragment.Bantuan.BantuanActivity;
import com.example.android.ayodolen.Fragment.Tiket.PilihTiketFragment;

public class CariTiketActivity extends AppCompatActivity {

    Toolbar toolbar;
    String namawisata;
    FrameLayout container;
    public Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_tiket);

        i = getIntent();
        toolbar = findViewById(R.id.toolbar);
        container =findViewById(R.id.frament_container);


        setSupportActionBar(toolbar);
        gantiTitel("Cari Tiket",i.getStringExtra("namawisata"));

        Bundle bundle = new Bundle();
        bundle.putString("id_wisata",i.getStringExtra("id_wisata"));
        PilihTiketFragment pilihFrag = new PilihTiketFragment();
        pilihFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,pilihFrag).commit();

        init_listener();
    }

    void init_listener(){

        //btn back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getFragmentManager().getBackStackEntryCount();

                if (count == 0) {
                    onBackPressed();
                    //additional code
                } else {
                    getFragmentManager().popBackStack();
                }
            }
        });


    }

    public void gantiTitel(String titel,String sub){
        getSupportActionBar().setTitle(titel);
        if (!sub.isEmpty()){
            getSupportActionBar().setSubtitle(sub);
        }else {
            getSupportActionBar().setSubtitle("");
        }
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_caritiket,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.bantuanmenu){
            Intent i = new Intent(CariTiketActivity.this,BantuanActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
//            gantiTitel("Cari Tiket",i.getStringExtra("namawisata"));
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

}
