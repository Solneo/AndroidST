package com.example.myapplicationst.NetCommunication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ыщвф on 15.10.2018.
 */

public class ModelOneObj {
    @SerializedName("title")
    @Expose
    private String titleObj;

    private String addr;
    private String rent_date;
    private String price;


    public String getTitleObj() {
        return titleObj;
    }

    public String getAddr() {
        return addr;
    }

    public String getRent_date() {
        return rent_date;
    }

    public String getPrice() {
        return price;
    }



    public void setTitleObj(String titleObj) {
        this.titleObj = titleObj;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
