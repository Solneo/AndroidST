package com.example.myapplicationst.LayoutActivity.CreateNewObj;

import android.Manifest;
import android.app.Activity;
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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Main.ThemeUtils;
import com.example.myapplicationst.NetCommunication.BodyClassForRequest.BodyCreate;
import com.example.myapplicationst.NetCommunication.Models.ModelAuth;
import com.example.myapplicationst.NetCommunication.NetUtil.RequestMultiBody;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.DataSaver;
import com.example.myapplicationst.UtilForDataSave.DatabaseHelper;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.myapplicationst.NetCommunication.NetUtil.RequestMultiBody.createPartFromString;

/**
 * Created by Ыщвф on 24.10.2018.
 */

public class CreateNewObjActivity extends Activity {
    int PICK_IMAGE = 0;
    private Intent intent = new Intent();
    File IMAGE_FILE;
    DataSaver dataSaver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_obj_activity);
        ThemeUtils.onActivityCreateSetTheme(this);

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
/*
    public void startResponseAuth() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.getCookieStore().removeAll();
        final RecuestBody recuestBody = new RecuestBody();
        String LOGIN = "root";
        String PASSWORD = "111222";
        recuestBody.setPassword("test");

        RequestMultiBody requestBody = new RequestMultiBody();
        File file = IMAGE_FILE; *//*new File(Environment.getExternalStorageDirectory(), "picture.jpg");*//*


        RequestBody username = createPartFromString(LOGIN);
        RequestBody password = createPartFromString(PASSWORD);
        RequestBody form_build_id = createPartFromString(AppNetCom.getStringToken());
        RequestBody form_id = createPartFromString("tm_order_form");
        RequestBody title = createPartFromString("root");
        RequestBody field_password = createPartFromString("111222");
        String pr = AppNetCom.getStringToken() + " null";


        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("form_build_id", form_build_id);
        params.put("form_id", form_id);
        params.put("title", title);
        params.put("field_password", field_password);
        *//*RequestBody user_id = params.put("user_id", userId);*//*
        AppNetCom.getApi().startAuth(params).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        session_name = response.body().getSession_name();
                        sessid = response.body().getSessid();
                        token = response.body().getToken();
                       *//* startResponseF();*//*
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
    }*/

/*

    public void startResponseF() {

        String cookieStr = AppNetCom.getStringCookie();
        AppNetCom.getApi().getToken(cookieStr).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                      */
/*  token = response.body().toString();*//*

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
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CreateNewObjActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });
    }
*/

    public void AuthAfter(View v) {
        RequestMultiBody requestBody = new RequestMultiBody();
        HashMap<String, RequestBody> params = new HashMap<>();

        String und = new String();
        und = "und";

        RequestBody type = createPartFromString("article");
        RequestBody title = createPartFromString("testWithHashMap");
        RequestBody body = createPartFromString("Body body body");
        params.put("type", type);
        params.put("title", title);
        params.put("body[und][0][value]", body);
        JSONObject jsonObject;

       /* jsonParams.put("username", "root");
        jsonParams.put("password", "111222");*/
        String cookieStr = AppNetCom.getStringCookie();
        String tokenG = AppNetCom.getStringToken();
        File file = IMAGE_FILE; /*new File(Environment.getExternalStorageDirectory(), "picture.jpg");*/
        BodyCreate b = new BodyCreate("NewTitle", "article", "qwerty");
        /*RequestBody user_id = params.put("user_id", userId);*/
        AppNetCom.getApi().setDataToNode(params, cookieStr, tokenG).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {

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
