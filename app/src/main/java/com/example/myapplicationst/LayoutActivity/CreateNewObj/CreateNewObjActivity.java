package com.example.myapplicationst.LayoutActivity.CreateNewObj;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Main.ThemeUtils;
import com.example.myapplicationst.NetCommunication.Adapters.AdapterForForm;
import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.ProviderToGlob;
import com.example.myapplicationst.NetCommunication.Models.ModelAuth;
import com.example.myapplicationst.NetCommunication.Models.ModelForm;
import com.example.myapplicationst.NetCommunication.Models.ModelPostAsk;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RequestMultiBody;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.DataSaver;
import com.example.myapplicationst.UtilForDataSave.DatabaseHelper;

import org.json.JSONObject;

import java.io.File;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.myapplicationst.NetCommunication.Models.SubModels.RequestMultiBody.createPartFromString;

/**
 * Created by Ыщвф on 24.10.2018.
 */

public class CreateNewObjActivity extends Activity implements ProviderToGlob {
    RecyclerView recyclerView;
    List<ModelPostAsk> post;
    List<ModelForm> postM;
    int PICK_IMAGE = 0;
    private Intent intent = new Intent();
    File IMAGE_FILE;
    DataSaver dataSaver;
    Context context;
    String token;
    String sessid;
    String session_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_obj_activity);
        ThemeUtils.onActivityCreateSetTheme(this);
        startResponseAuth();
       /* startResponseF();*/
    }

    public void GalerySelect(View v) {
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        /*saveDataInAct();*/
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted in act");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted sdk<23 in act");
            return true;
        }
    }

    public void saveDataInAct() {
        try {
            isStoragePermissionGranted();
            DatabaseHelper databaseHelper = new DatabaseHelper(this);

            SQLiteDatabase myDB = databaseHelper.getWritableDatabase();
                    /*openOrCreateDatabase("my.db", 0x000, null);*/

            dataSaver = new DataSaver(myDB, this);
            dataSaver.setData();
            dataSaver.getData();
            myDB.close();


        } catch (Exception e) {
            Log.i("myerrorDataSaver:", e.getMessage());
        }
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

    public void startResponseAuth() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.getCookieStore().removeAll();
        final RecuestBody recuestBody = new RecuestBody();
        String LOGIN = "Sod";
        String PASSWORD = "123456";
        recuestBody.setPassword("test");

        RequestMultiBody requestBody = new RequestMultiBody();
        File file = IMAGE_FILE; /*new File(Environment.getExternalStorageDirectory(), "picture.jpg");*/


        RequestBody username = createPartFromString(LOGIN);
        RequestBody password = createPartFromString(PASSWORD);
        RequestBody form_build_id = createPartFromString(AppNetCom.getStringToken());
        RequestBody form_id = createPartFromString("tm_order_form");
        RequestBody title = createPartFromString("qwer");
        RequestBody field_password = createPartFromString("123456");
        String pr = AppNetCom.getStringToken() + " null";
        Log.i("myerr", pr);
      /*  RequestBody userId = createPartFromString(mUserId + "");*/

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("form_build_id", form_build_id);
        params.put("form_id", form_id);
        params.put("title", title);
        params.put("field_password", field_password);
        /*RequestBody user_id = params.put("user_id", userId);*/
        AppNetCom.getApi().setDataSin(params).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        session_name = response.body().getSession_name();
                        sessid = response.body().getSessid();
                        token = response.body().getToken();
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
            public void onFailure(Call<ModelAuth> call, Throwable t) {
                Toast.makeText(CreateNewObjActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });
    }

    @Override
    public void provideGlob(String s) {
        AppNetCom appNetCom = (AppNetCom) getApplication();
        Log.i("qwe", s);
        ((AppNetCom) getApplication()).setStringToken(s);
    }

    public void startResponseF() {
        postM = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_createobj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterForForm adapt = new AdapterForForm(postM, this, this);
        recyclerView.setAdapter(adapt);

        AppNetCom.getApi().getDataF().enqueue(new Callback<List<ModelForm>>() {

            @Override
            public void onResponse(Call<List<ModelForm>> call, Response<List<ModelForm>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        postM.addAll(response.body());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    } else {
                        Log.i("myerr", response.message());
                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ModelForm>> call, Throwable t) {
                Toast.makeText(CreateNewObjActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }

        });
    }

    public void AuthAfter(View v) {
        Map<String, Object> jsonParams = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            jsonParams = new ArrayMap<>();
        }
//put something inside the map, could be null
        String post = new String();
        post = "post";
        jsonParams.put("type", post);
        jsonParams.put("username", "Sod");


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        final RecuestBody recuestBody = new RecuestBody();
        recuestBody.setLogin("test");
        recuestBody.setPassword("test");
        String cookieStr = sessid + "=" + session_name;

        Log.i("myinfo", cookieStr);
        RequestMultiBody requestBody = new RequestMultiBody();
        File file = IMAGE_FILE; /*new File(Environment.getExternalStorageDirectory(), "picture.jpg");*/
        RequestBody tokenP = createPartFromString(token);
        RequestBody sessidP = createPartFromString(sessid);
        RequestBody session_nameP = createPartFromString(session_name);

/*
        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("token", tokenP);*/


        /*RequestBody user_id = params.put("user_id", userId);*/
        AppNetCom.getApi().setDataSinWithCookie(body, cookieStr).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        session_name = response.body().getSession_name();
                        sessid = response.body().getSessid();
                        token = response.body().getToken();
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
            public void onFailure(Call<ModelAuth> call, Throwable t) {
                Toast.makeText(CreateNewObjActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });
    }

}
