package com.example.android.ayodolen.Rest;

import com.example.android.ayodolen.Model.EditUser;
import com.example.android.ayodolen.Model.GetPembayaran;
import com.example.android.ayodolen.Model.GetTiket;
import com.example.android.ayodolen.Model.GetWisata;
import com.example.android.ayodolen.Model.KategoriResponse;
import com.example.android.ayodolen.Model.KomentarResponse;
import com.example.android.ayodolen.Model.PembayaranResponse;
import com.example.android.ayodolen.Model.RegistrasiUser;
import com.example.android.ayodolen.Model.TiketResponse;
import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.Model.WisataResponse;
import com.example.android.ayodolen.RegisterActivity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by user on 15/11/2018.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> loginRequest(@Field("email") String username,
                                    @Field("password") String password);


    @FormUrlEncoded
    @POST("register")
    Call<RegistrasiUser> registrasiUser(@Field("email") String email,
                                        @Field("password") String password,
                                        @Field("nohp") String nohp,
                                        @Field("nama") String nama);

//    @FormUrlEncoded
//    @POST("login")
//    Call<UserResponse> loginRequest(@Field("username") String username,
//                                    @Field("password") String password);

    @FormUrlEncoded
    @PUT("register")
    Call<EditUser> editUser( @Field("id_user") String id_user,
                                  @Field("username") String username,
                                  @Field("password") String password,
                                  @Field("nama") String nama);


    //semua wisata
    @GET("wisata") Call<GetWisata> getWisata();

    //wisata kategori
    @FormUrlEncoded
    @POST("wisata/kategori") Call<GetWisata> getWisataKategori(@Field("id_kategori") String id_kategori);

    //wisata byid
    @FormUrlEncoded
    @POST("wisata/wisataby") Call<WisataResponse> getWisataby(@Field("id") String id_wisata);

    //Tiket termurah byid
    @FormUrlEncoded
    @POST("Tiket/tikettermurah") Call<TiketResponse> tiketmurahby(@Field("id") String id_wisata);

    //Tiket byid
    @FormUrlEncoded
    @POST("Tiket/gettiketby") Call<GetTiket> tiketbyid(@Field("id") String id_wisata);

    //pembayaran byid
    @FormUrlEncoded
    @POST("Pembayaran/pembayaranbyid") Call<PembayaranResponse> pembayaranbyid(@Field("id") String nomortransaksi);


//pembayaran byUser
    @FormUrlEncoded
    @POST("Pembayaran/pembayaranuser") Call<GetPembayaran> pembayaranbyuser(@Field("id") String idUser);


// merubah setatus pembayarn
    @FormUrlEncoded
    @POST("Pembayaran/statusPembayaran") Call<PembayaranResponse> merubahStatusTransaksi(@Field("id") String no_transaksi,@Field("status") String status);


//    proses pembayaran
    @FormUrlEncoded
    @POST("Pembayaran") Call<PembayaranResponse> prosesPembayaran(@Field("id_wisata") String id_wisata,
                                                                       @Field("id_tiket") String id_tiket,
                                                                       @Field("id_user") String id_user,
                                                                       @Field("totaltiket") String totaltiket,
                                                                       @Field("tanggalkunjungan") String tanggalkunjungan,
                                                                       @Field("id_metodebayar") String id_metodebayar,
                                                                       @Field("biayalayanan") String biayalayanan,
                                                                      @Field("totalbayar") String totalbayar,
                                                                      @Field("fee") String fee
                                                                  );



    //kategori
    @GET("kategori") Call<KategoriResponse> getKategori();

    //komentar
    @FormUrlEncoded
    @POST("komentar/komentar") Call<KomentarResponse> getKomentar(@Field("id_wisata") String id_wisata);


    @FormUrlEncoded
    @POST("komentar/insert") Call<KomentarResponse> tambahKomentar(@Field("id_wisata") String id_wisata,
                                                                   @Field("id_user") String id_user,
                                                                   @Field("komentar") String komentar,
                                                                   @Field("tanggal") String tanggal);


    ///userdetail
    @FormUrlEncoded
    @POST("Login/userdetail") Call<UserResponse> getdetailuser(@Field("id_user") String id_user);





}
