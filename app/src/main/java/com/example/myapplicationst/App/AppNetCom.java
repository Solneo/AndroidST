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
    private static String stringToken;
    private static String stringCookie;
    private static Boolean Auth;


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
                .baseUrl("http://dalen.iqads.ru")
                /*.baseUrl("http://httpbin.org")*/
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        serviceAPIConnect = retrofit.create(ServiceAPIConnect.class);
        setStringId("15");
        setStringToken("null");
        setAuth(false);
        setStringToken(null);
        setStringCookie(null);
    }

    public static void setStringCookie(String stringCookie) {
        AppNetCom.stringCookie = stringCookie;
    }

    public static String getStringCookie() {
        return stringCookie;
    }

    public static Boolean getAuth() {
        return Auth;
    }

    public static void setAuth(Boolean auth) {
        Auth = auth;
    }

    public static void setStringId(String stringId) {
        AppNetCom.stringId = stringId;
    }

    public static String getStringId() {
        return stringId;
    }

    public static void setStringToken(String stringToken) {
        AppNetCom.stringToken = stringToken;
    }

    public static String getStringToken() {
        return stringToken;
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
