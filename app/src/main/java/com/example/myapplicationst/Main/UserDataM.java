package com.example.myapplicationst.Main;

import android.content.Context;

import com.example.myapplicationst.UtilForDataSave.PrefSaver;

/**
 * Created by Ыщвф on 13.11.2018.
 */

public class UserDataM {

    private PrefSaver prefSaver;
    private String USERNAME;
    private String EMAIL;

    public UserDataM(Context context) {
        prefSaver = new PrefSaver(context);
        USERNAME = "username";
        EMAIL = "email";
    }

    public void setUsername(String username) {
        prefSaver.saveData(USERNAME, username);
    }

    public String getUsername() {
        return prefSaver.loadData(USERNAME);
    }

    public void setEmail(String email) {
        prefSaver.saveData(USERNAME, email);
    }

    public String getEmail() {
        return prefSaver.loadData(EMAIL);
    }

    public void setMainData() {

    }

}
