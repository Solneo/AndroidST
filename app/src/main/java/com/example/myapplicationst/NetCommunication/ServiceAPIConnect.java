package com.example.myapplicationst.NetCommunication;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public interface ServiceAPIConnect {
    @GET("/app")
    Call<List<PostModel>> getData();
    @GET("/app/{id}")
    Call<List<ModelOneObj>> getDat(@Path("id") String id);
}
