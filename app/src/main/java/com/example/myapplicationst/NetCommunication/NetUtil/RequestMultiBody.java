package com.example.myapplicationst.NetCommunication.NetUtil;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.net.URLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Ыщвф on 31.10.2018.
 */

public class RequestMultiBody {
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    public static RequestBody createPartFromStringList(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);

    }
    /*public static RequestBody createPartFromStringJSON(String descriptionString) {
        return RequestBody.create(
                okhttp3..FORM, descriptionString);
    }*/
    @NonNull
    public static MultipartBody.Part prepareFilePart(Context context, String partName, File file) {

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse(mimeType),file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
