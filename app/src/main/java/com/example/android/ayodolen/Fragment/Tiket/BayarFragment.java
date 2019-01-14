package com.example.android.ayodolen.Fragment.Tiket;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.R;

public class BayarFragment extends Fragment {
    View v;Bundle bndl;
    LinearLayout btnMinimarket;
    RelativeLayout btnTransferBank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bayar, container, false);
        bndl = getArguments();

        btnMinimarket = v.findViewById(R.id.bayar_minimarket);
        btnTransferBank = v.findViewById(R.id.bayar_transferbank);

        btnTransferBank.setEnabled(false);

        initListener();
        return v;
    }


    void initListener(){

        btnMinimarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_bayar_minimarket);

                RelativeLayout btnAlfamart = dialog.findViewById(R.id.btn_alfamarta);

                btnAlfamart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        bndl.putString("id_layanan","2");
                        bndl.putString("layanan","Alfamart");
                        bndl.putString("biayalayanan","2500");

                        MetodePembayaranFragment pilMetPem = new MetodePembayaranFragment();
                        pilMetPem.setArguments(bndl);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frament_container,pilMetPem).addToBackStack(null).commit();
                    }
                });

                dialog.show();
            }
        });

        btnTransferBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_bayar_transferbank);

                dialog.show();
            }
        });

    }




    @Override
    public void onStart() {
        CariTiketActivity activitymain = (CariTiketActivity) getActivity();
        activitymain.gantiTitel("Pembayaran","");
        super.onStart();
    }
}
