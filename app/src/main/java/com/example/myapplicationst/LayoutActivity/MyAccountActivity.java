package com.example.myapplicationst.LayoutActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Main.LetterBitmap;
import com.example.myapplicationst.NetCommunication.Models.ModelAccUser;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.PrefSaver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 11.11.2018.
 */

public class MyAccountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        startLoadUserData();
    }

    void startLoadUserData() {
        PrefSaver prefSaver = new PrefSaver(this);
        String uid = new String();
        uid = prefSaver.loadData("uid");

        AppNetCom.getApi().getUserData(uid, AppNetCom.getStringCookie(), AppNetCom.getStringToken()).enqueue(new Callback<ModelAccUser>() {
            @Override
            public void onResponse(Call<ModelAccUser> call, Response<ModelAccUser> response) {
                if (response.body() != null) {
                    bindUserData(
                            response.body().getName(),
                            response.body().getMail(),
                            response.body().getTimezone());
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<ModelAccUser> call, Throwable t) {
                Toast.makeText(MyAccountActivity.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });
    }

    void bindUserData(String name, String mail, String timezone) {
        TextView textName = (TextView) findViewById(R.id.user_name_inacc);
        TextView textEmail = (TextView) findViewById(R.id.user_mail_inacc);
        TextView textTimezone = (TextView) findViewById(R.id.user_timezone_inacc);
        ImageView imageAvatar = (ImageView) findViewById(R.id.image_user_ava);
        textName.setText(name);
        textEmail.setText(mail);
        textTimezone.setText(timezone);
        setAva(imageAvatar, name);
    }

    void setAva(ImageView v, String s) {
        int COVER_IMAGE_SIZE = 200; //in pixels
        LetterBitmap letterBitmap = new LetterBitmap(this);
        Bitmap letterTile = letterBitmap.getLetterTile(s, s, COVER_IMAGE_SIZE, COVER_IMAGE_SIZE);
        v.setImageBitmap(letterTile);
    }

    public void logOut(View v) {
        PrefSaver prefSaver = new PrefSaver(this);
        AppNetCom.setAuth(false);
        AppNetCom.setStringCookie(null);
        AppNetCom.setStringToken(null);
        prefSaver.saveData("auth", null);
        prefSaver.saveData("token", null);
        prefSaver.saveData("cookie", null);
        prefSaver.saveData("username", null);
        prefSaver.saveData("email", null);
        finish();
    }
}
