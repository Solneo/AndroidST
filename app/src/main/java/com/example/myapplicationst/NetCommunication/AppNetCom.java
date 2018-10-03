package com.example.myapplicationst.NetCommunication;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public class AppNetCom extends Application {
    private static ServiceAPIConnect serviceAPIConnect;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceAPIConnect = retrofit.create(ServiceAPIConnect.class);
    }
    public static ServiceAPIConnect getApi(){
        return serviceAPIConnect;
    }
}
