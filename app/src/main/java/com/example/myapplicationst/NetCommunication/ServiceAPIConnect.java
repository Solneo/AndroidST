package com.example.myapplicationst.NetCommunication;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ыщвф on 02.10.2018.
 */

public interface ServiceAPIConnect {
    @GET("/")
    Call<List<PostModel>> listRepos(@Path("user") String user);
}
