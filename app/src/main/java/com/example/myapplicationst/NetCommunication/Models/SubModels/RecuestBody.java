package com.example.myapplicationst.NetCommunication.Models.SubModels;

import okhttp3.RequestBody;

/**
 * Created by Ыщвф on 26.10.2018.
 */

public class RecuestBody {
    private String login;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
