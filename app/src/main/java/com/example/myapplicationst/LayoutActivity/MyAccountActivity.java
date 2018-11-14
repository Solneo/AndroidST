package com.example.myapplicationst.LayoutActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.PrefSaver;

/**
 * Created by Ыщвф on 11.11.2018.
 */

public class MyAccountActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
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
