package com.example.myapplicationst.NetCommunication;



import com.example.myapplicationst.NetCommunication.Models.ModelOneObj;
import com.example.myapplicationst.NetCommunication.Models.ModelPostAsk;
import com.example.myapplicationst.NetCommunication.Models.PostModel;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public interface ServiceAPIConnect {
    @GET("/app")
    Call<List<PostModel>> getData();
    @GET("/app/{id}")
    Call<List<ModelOneObj>> getDat(@Path("id") String id);

    /*@FormUrlEncoded*/
    @Multipart
    @POST("/forpost")
    Call<List<ModelPostAsk>> setData(
            /*@Field("login") String apiKey,
            @Field("password") String appId*/
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file
    );
}
