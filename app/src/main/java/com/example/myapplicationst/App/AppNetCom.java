package com.example.myapplicationst.App;

import android.app.Application;
import android.content.res.Configuration;

import com.example.myapplicationst.NetCommunication.ServiceAPIConnect;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public class AppNetCom extends Application {
    private static ServiceAPIConnect serviceAPIConnect;
    private Retrofit retrofit;
    private static String stringId;


    @Override
    public final void onCreate() {
        super.onCreate();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                /*.baseUrl("https://novorossiysk.33kvartiry.ru")*/
                .baseUrl("http://betta.iqads.ru")
                /*.baseUrl("http://httpbin.org")*/
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        serviceAPIConnect = retrofit.create(ServiceAPIConnect.class);
        setStringId("15");


    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public static String getStringId() {
        return stringId;
    }

    public static ServiceAPIConnect getApi() {
        return serviceAPIConnect;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
