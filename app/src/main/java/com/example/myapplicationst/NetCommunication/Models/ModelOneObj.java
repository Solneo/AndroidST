package com.example.myapplicationst.NetCommunication.Models;

import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ыщвф on 15.10.2018.
 */

public class ModelOneObj {
    @SerializedName("title")
    @Expose
    private String titleObj;
    @SerializedName("field_addresss")
    @Expose
    private String addr;
    @SerializedName("field_rent_date")
    @Expose
    private String rent_date;
    @SerializedName("field_price")
    @Expose
    private String price;
    @SerializedName("field_photo")
    @Expose
    private Images[] imagesList;
    @SerializedName("field_desc")
    @Expose
    private String bigText;

    public Images[] getImagesList() {
        return imagesList;
    }

    public void setImagesList(Images[] thImagesList) {
        this.imagesList = thImagesList;
    }

    /*  public void setImages(Images images) {
          this.images = images;
      }*/
    public String getBigText() {
        return bigText;
    }

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

    public void setBigText(String bigText) {
        this.bigText = bigText;
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
