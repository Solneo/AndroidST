package com.example.myapplicationst.NetCommunication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ыщвф on 08.11.2018.
 */

public class ModelAuth {
    @SerializedName("sessid")
    @Expose
    private String sessid;
    @SerializedName("session_name")
    @Expose
    private String session_name;
    @SerializedName("token")
    @Expose
    private String token;

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_name() {
        return session_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public String getSessid() {
        return sessid;
    }
}
