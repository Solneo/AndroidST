package com.example.myapplicationst.NetCommunication.Models;

/**
 * Created by Ыщвф on 02.10.2018.
 */

import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {
    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("field_price")
    @Expose
    private String price;

    @SerializedName("field_rent_date")
    @Expose
    private String rent_date;
    @SerializedName("field_addresss")
    @Expose
    private String addr;
    @SerializedName("nid")
    @Expose
    private String objId;

    @SerializedName("field_photo")
    @Expose
    private Images images;


    @SerializedName("link")
    @Expose
    private Object link;
    @SerializedName("elementPureHtml")
    @Expose
    private String elementPureHtml;




    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }
    public void setobjId(String objId) {
        this.objId = objId;
    }

    public String getobjId() {
        return objId;
    }
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }
    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

   public void setImages(Images images) {
        this.images = images;
    }
    /*public void setListImages(List<Images> images) {

        for (Images i: images) {
            this.images = i;
        }

    }*/


    public Images getImages() {
        return images;
    }

    public String getImage() {

        return images.getSrc();
    }


    public String getElementPureHtml() {
        return elementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }
}

