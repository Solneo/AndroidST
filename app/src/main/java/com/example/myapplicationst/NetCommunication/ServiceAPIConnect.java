package com.example.myapplicationst.NetCommunication;


import com.example.myapplicationst.NetCommunication.Models.ModelAccUser;
import com.example.myapplicationst.NetCommunication.Models.ModelAuth;
import com.example.myapplicationst.NetCommunication.Models.ModelForm;
import com.example.myapplicationst.NetCommunication.Models.ModelOneObj;
import com.example.myapplicationst.NetCommunication.Models.PostModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public interface ServiceAPIConnect {
    @GET("/jsonapartment")
    Call<List<PostModel>> getData();

    @GET("/jsonapartment/{id}")
    Call<List<ModelOneObj>> getDat(@Path("id") String id);

    @GET("/rest/user/{id}")
    Call<ModelAccUser> getUserData(@Path("id") String id,
                                   @Header("Cookie") String cookie,
                                   @Header("X-CSRF-TOKEN") String token);


    @POST("/forpost")
    Call<List<ModelForm>> getDataF();

    /*@FormUrlEncoded*//*

    @Multipart
    @POST("/testapi/user/login")
    Call<List<ModelPostAsk>> setData(
            */
/*@Field("login") String apiKey,
            @Field("password") String appId*//*

            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file
    );
*/
    @Multipart
    @POST("/rest/user/login")
    Call<ModelAuth> startAuth(@PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/rest/node")
    Call<ModelAuth> setDataToNode(@PartMap() Map<String, RequestBody> partMap,
                                  @Header("Cookie") String cookie,
                                  @Header("X-CSRF-TOKEN") String token);


}
