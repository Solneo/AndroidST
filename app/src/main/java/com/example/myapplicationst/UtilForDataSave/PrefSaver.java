package com.example.myapplicationst.UtilForDataSave;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ыщвф on 13.11.2018.
 */

public class PrefSaver {
    private Context context;
    private SharedPreferences sPref;

    public PrefSaver(Context context) {
        super();
        this.context = context;
    }

    public void saveData(String key, String value) {
        sPref = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public String loadData(String key) {
        sPref = context.getSharedPreferences(key, context.MODE_PRIVATE);
        String savedText = sPref.getString(key, null);
        return savedText;
    }

}
