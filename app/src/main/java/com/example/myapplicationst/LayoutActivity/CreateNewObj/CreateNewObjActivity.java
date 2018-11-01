package com.example.myapplicationst.LayoutActivity.CreateNewObj;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.NetCommunication.Adapters.AdapterForYoken;
import com.example.myapplicationst.NetCommunication.Models.ModelPostAsk;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RequestMultiBody;
import com.example.myapplicationst.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplicationst.NetCommunication.Models.SubModels.RequestMultiBody.createPartFromString;

/**
 * Created by Ыщвф on 24.10.2018.
 */

public class CreateNewObjActivity extends Activity {
    RecyclerView recyclerView;
    List<ModelPostAsk> post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_obj_activity);
        startResponse();
    }

    public void startResponse() {
        post = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_createobj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterForYoken adapt = new AdapterForYoken(post, this);
        recyclerView.setAdapter(adapt);
        final RecuestBody recuestBody = new RecuestBody();
        recuestBody.setLogin("MyTestLogin");
        recuestBody.setPassword("MyTestPassword");


        RequestMultiBody requestBody = new RequestMultiBody();
        File file = new File(Environment.getExternalStorageDirectory(), "picture.jpg");

        MultipartBody.Part body = RequestMultiBody.prepareFilePart(this, "image", file);

        RequestBody login = createPartFromString(/*mEdtLogin.getText().toString()*/recuestBody.getLogin());
        RequestBody password = createPartFromString(/*mEdtPassword.getText().toString()*/recuestBody.getPassword());
      /*  RequestBody userId = createPartFromString(mUserId + "");*/

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        /*RequestBody user_id = params.put("user_id", userId);*/

        AppNetCom.getApi().setData(params, body).enqueue(new Callback<List<ModelPostAsk>>() {
            @Override
            public void onResponse(Call<List<ModelPostAsk>> call, Response<List<ModelPostAsk>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            post.addAll(response.body());
                            recyclerView.getAdapter().notifyDataSetChanged();
                            Log.i("qwe", recuestBody.toString());
                        } else {
                            okhttp3.Request request;
                            request = call.request();
                            Log.i("qwer", request.toString());
                        }
                    } else {
                        Log.i("qwert", response.message());
                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwerty", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ModelPostAsk>> call, Throwable t) {
                Toast.makeText(CreateNewObjActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }

        });
    }
}
