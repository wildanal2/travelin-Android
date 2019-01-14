package com.example.android.ayodolen.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 15/11/2018.
 */

public class ApiClient {
//    public static final String BASE_URL = "https://travelinserver.000webhostapp.com/";


    public static final String BASE_URL = "https://travelinku.000webhostapp.com/";
    public static final String BASE_URL1 = "https://travelinku.000webhostapp.com/index.php/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){

            OkHttpClient client = new OkHttpClient();

            Gson gson = new GsonBuilder()
                    .setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
