package com.example.myapplicationst.LayoutActivity.CreateNewObj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Main.ThemeUtils;
import com.example.myapplicationst.NetCommunication.Adapters.AdapterForYoken;
import com.example.myapplicationst.NetCommunication.Models.ModelPostAsk;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RequestMultiBody;
import com.example.myapplicationst.R;
import com.google.android.gms.common.internal.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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
    int PICK_IMAGE = 0;
    private Intent intent = new Intent();
    File IMAGE_FILE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_obj_activity);
        ThemeUtils.onActivityCreateSetTheme(this);
    }

    public void GalerySelect(View v) {
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap img = null;
        if (requestCode == PICK_IMAGE && data != null) {
            Uri selectedImage = data.getData();
            // create file
            Cursor c = getContentResolver().query(selectedImage, null, null, null, null);
            c.moveToFirst();
            String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
            File file = new File(ImagePickUpUtil.getRealPathFromURI(this, selectedImage));
            ImageView imageView = findViewById(R.id.imageView_prev);
            IMAGE_FILE = file;
            imageView.setImageURI(android.net.Uri.parse(file.getPath()));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startResponseCR(View v) {

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
        File file = IMAGE_FILE; /*new File(Environment.getExternalStorageDirectory(), "picture.jpg");*/

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
