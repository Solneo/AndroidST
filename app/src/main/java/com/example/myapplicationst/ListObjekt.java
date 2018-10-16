package com.example.myapplicationst;

import android.app.Activity;
import android.content.Context;
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
import com.example.myapplicationst.NetCommunication.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 28.09.2018.
 */

public class ListObjekt extends Activity implements RecyclerViewClickListener{
    RecyclerView recyclerView;
    List<PostModel> posts = new ArrayList<>();
    AdapterResponse adapter;
   Context context;
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
   @Override
   public void recyclerViewListClicked(View v, int position, String str){
       AppNetCom appNetCom = (AppNetCom) getApplication();
       ((AppNetCom) getApplication()).setStringId(str);
       Intent intent = new Intent(this, OneObject.class);
       startActivity(intent);
   }
    public void startResponse() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new AdapterResponse(posts, context, this);
        recyclerView.setAdapter(adapter);
      /*  try {
            Response response = AppNetCom.getApi().getData("app",50).execute();
        } catch (Exception e) {

        }*/

        AppNetCom.getApi().getData().enqueue(new Callback<List<PostModel>>() {

            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.body() != null) {
                    posts.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();

                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(ListObjekt.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });

    }

    public void goToOneObjectWithID(View v) {

       /*recyclerView.
        AppNetCom appNetCom = (AppNetCom) getApplication();
        ((AppNetCom) getApplication()).setStringId();
        Intent intent = new Intent(this, OneObject.class);
        startActivity(intent);*/
    }
}
