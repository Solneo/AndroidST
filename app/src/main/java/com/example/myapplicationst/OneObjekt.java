package com.example.myapplicationst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationst.NetCommunication.AdapterResponse;
import com.example.myapplicationst.NetCommunication.PostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 28.09.2018.
 */

public class OneObjekt extends Activity {
    RecyclerView recyclerView;
    List<PostModel> posts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_objekt_layout);

        startResponse();
    }

    public void onBackPressedButton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

   /* public void onButtonStartResponce(View v) {
        startResponse();
    }*/

    public void startResponse() {
        posts = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterResponse adapter = new AdapterResponse(posts);
        recyclerView.setAdapter(adapter);
      /*  try {
            Response response = AppNetCom.getApi().getData("app",50).execute();
        } catch (Exception e) {

        }*/

        AppNetCom.getApi().getData("bash",50).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                posts.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(OneObjekt.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf",t.getMessage());
            }
        });
    }
}
