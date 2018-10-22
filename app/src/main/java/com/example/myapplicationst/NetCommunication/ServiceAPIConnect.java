package com.example.myapplicationst.NetCommunication;



import com.example.myapplicationst.NetCommunication.Models.ModelOneObj;
import com.example.myapplicationst.NetCommunication.Models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public interface ServiceAPIConnect {
    @GET("/app")
    Call<List<PostModel>> getData();
    @GET("/app/{id}")
    Call<List<ModelOneObj>> getDat(@Path("id") String id);
}
