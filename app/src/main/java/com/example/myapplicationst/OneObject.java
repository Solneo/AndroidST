package com.example.myapplicationst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationst.NetCommunication.AdapterForOneObj;
import com.example.myapplicationst.NetCommunication.AdapterResponse;
import com.example.myapplicationst.NetCommunication.ModelOneObj;
import com.example.myapplicationst.NetCommunication.PostModel;
import com.example.myapplicationst.NetCommunication.ServiceAPIConnect;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public class OneObject extends Activity {
    RecyclerView recyclerView;
    List<ModelOneObj> post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_object_activity);

        startResponse();
    }

    public void onBackPressedButton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startResponse() {

        post = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_oneobj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterForOneObj adapt = new AdapterForOneObj(post);
        recyclerView.setAdapter(adapt);

        AppNetCom.getApi().getDat(AppNetCom.getStringId()).enqueue(new Callback<List<ModelOneObj>>() {


            @Override
            public void onResponse(Call<List<ModelOneObj>> call, Response<List<ModelOneObj>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            post.addAll(response.body());
                            recyclerView.getAdapter().notifyDataSetChanged();
                        } else {
                            okhttp3.Request request;
                            request = call.request();
                            Log.i("qwe", request.toString());
                        }
                    }
                    else {
                        Log.i("qwe", response.message());
                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ModelOneObj>> call, Throwable t) {
                Toast.makeText(OneObject.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });
    }


}

