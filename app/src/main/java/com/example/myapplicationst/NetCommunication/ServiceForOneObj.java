package com.example.myapplicationst.NetCommunication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public interface ServiceForOneObj {
    @GET("/app/{id}")
    Call<List<PostModel>> getDat(@Path("id") int id);
}
