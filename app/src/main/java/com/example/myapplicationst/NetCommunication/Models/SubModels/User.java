package com.example.myapplicationst.NetCommunication.Models.SubModels;

/**
 * Created by Ыщвф on 13.11.2018.
 */

public class User {
    private String uid;
    private String name;
    private String mail;
    private String timezone;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getUid() {
        return uid;
    }
}
