package com.example.android.ayodolen.Fragment.Tiket;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.BayarActivity;
import com.example.android.ayodolen.CariTiketActivity;
import com.example.android.ayodolen.Model.Pembayaran;
import com.example.android.ayodolen.Model.PembayaranResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class MetodePembayaranFragment extends Fragment {
    View v;DialogPlus dialogp; Bundle bndl;
    private ApiInterface mApiInterface;
    Button btnBayar;
    TextView tvTotharga,tvNamaTiket,tvHargaSatuan,tvTotTiket,tvBiayaLayanan,tvSubtotal,tvTotharga2,tvKetLayanan;
    NumberFormat formatRupiah;Locale localeID;
    Integer total=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_metode_pembayaran, container, false);
        bndl = getArguments();

        tvTotharga = v.findViewById(R.id.tv_totharga);
        btnBayar = v.findViewById(R.id.btn_bayar);
        tvNamaTiket = v.findViewById(R.id.tv_namatiket);
        tvHargaSatuan = v.findViewById(R.id.tv_hargasatuan);
        tvTotTiket = v.findViewById(R.id.tv_jumlahtiket);
        tvSubtotal = v.findViewById(R.id.tv_subtotaltiket);
        tvKetLayanan = v.findViewById(R.id.tv_ketlayanan);
        tvBiayaLayanan = v.findViewById(R.id.tv_hargalayanan);
        tvTotharga2 = v.findViewById(R.id.tv_hargatotal2);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        final SessionManagement ses = new SessionManagement(getContext());
        localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        total = Integer.parseInt(bndl.getString("hargatotal"))+Integer.parseInt(bndl.getString("biayalayanan"));



        tvTotharga.setText(formatRupiah.format(total));
        tvNamaTiket.setText(bndl.getString("nama")+" - "+bndl.getString("jenistiket")+" - "+bndl.getString("type"));
        tvHargaSatuan.setText("@"+formatRupiah.format(Integer.parseInt(bndl.getString("hargasatuan"))));
        tvTotTiket.setText("Harga ("+bndl.getString("jumlah")+" Tickets)");
        tvSubtotal.setText(formatRupiah.format(Integer.parseInt(bndl.getString("hargatotal"))));
        tvKetLayanan.setText("Biaya Layanan("+bndl.getString("layanan")+")");
        tvBiayaLayanan.setText(formatRupiah.format(Integer.parseInt(bndl.getString("biayalayanan"))));
        tvTotharga2.setText(formatRupiah.format(total));


        dialogp = DialogPlus.newDialog(getActivity())
                .setGravity(Gravity.TOP)
                .setContentHolder(new ViewHolder(R.layout.menu_konfirmasibayar_minimarket))
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();


        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout btnBatal = (RelativeLayout) dialogp.findViewById(R.id.btn_batal);
                RelativeLayout btnBayar = (RelativeLayout) dialogp.findViewById(R.id.btn_bayar);

                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogp.dismiss();
                    }
                });

                btnBayar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog loading = new Dialog(getContext());
                        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        loading.setContentView(R.layout.item_loading);
                        loading.setCancelable(false);

                        loading.show();
                        dialogp.dismiss();
                        CariTiketActivity actMain = (CariTiketActivity) getActivity();

                        Call<PembayaranResponse> procesBayar = mApiInterface.prosesPembayaran(actMain.i.getStringExtra("id_wisata"),
                                                                                                bndl.getString("id"),
                                                                                                ses.getKeyId(),
                                                                                                bndl.getString("jumlah"),
                                                                                                bndl.getString("tgl"),
                                                                                                bndl.getString("id_layanan"),
                                                                                                bndl.getString("biayalayanan"),
                                                                                                total.toString(),
                                                                                                "0"
                                );
                        procesBayar.enqueue(new Callback<PembayaranResponse>() {
                            @Override
                            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                                Pembayaran pem = response.body().getmPembayaran();
                                loading.hide();

                                Intent i = new Intent(getActivity(),BayarActivity.class);
                                i.putExtra("id",pem.getId());
                                getActivity().startActivity(i);
                                getActivity().finish();
                            }

                            @Override
                            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                                Log.e(TAG, "onFailure: "+t.getMessage() );
                                if (getContext()!=null){
                                    Toast.makeText(getContext(), "Pastikan Anda memiliki koneksi yang stabil", Toast.LENGTH_LONG).show();
                                }

                                loading.hide();
                            }
                        });

                    }
                });

                dialogp.show();
            }
        });

        return v;
    }




}
