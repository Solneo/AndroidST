package com.example.myapplicationst;

import android.app.Application;
import android.content.res.Configuration;

import com.example.myapplicationst.NetCommunication.ServiceAPIConnect;
import com.google.android.gms.flags.Singletons;

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
        retrofit = new Retrofit.Builder()
                .baseUrl("https://novorossiysk.33kvartiry.ru")
                .addConverterFactory(GsonConverterFactory.create())
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
