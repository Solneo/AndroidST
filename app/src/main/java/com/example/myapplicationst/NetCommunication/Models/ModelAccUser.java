package com.example.myapplicationst.NetCommunication.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ыщвф on 16.11.2018.
 */

public class ModelAccUser {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }
}
