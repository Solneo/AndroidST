package com.example.myapplicationst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.myapplicationst.NetCommunication.AppNetCom;
import com.example.myapplicationst.NetCommunication.PostModel;
import com.example.myapplicationst.NetCommunication.ServiceAPIConnect;

import java.util.List;

/**
 * Created by Ыщвф on 28.09.2018.
 */

public class OneObjekt extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_objekt_layout);
    }
    public void onBackPressedButton(View v){
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
