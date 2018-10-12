package com.example.myapplicationst.NetCommunication;

/**
 * Created by Ыщвф on 02.10.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {
    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link")
    @Expose
    private Object link;
    @SerializedName("elementPureHtml")
    @Expose
    private String elementPureHtml;



    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }
}

