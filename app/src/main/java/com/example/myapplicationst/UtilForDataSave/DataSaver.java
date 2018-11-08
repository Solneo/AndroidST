package com.example.myapplicationst.UtilForDataSave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by Ыщвф on 05.11.2018.
 */

public class DataSaver {
    private SQLiteDatabase myDB;
    private Context context;
    private DatabaseErrorHandler er;

    public DataSaver(SQLiteDatabase myDB, Context context) {
        this.myDB = myDB;
        this.context = context;
        er = null;

    }

   /* String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.RECORD_AUDIO,
    };

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this.context, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        return true;
    }*/

    public void setData() {
        try {
            /*checkPermissions();*/

          /*  myDB = SQLiteDatabase.openOrCreateDatabase("my.db", null, context.MODE_PRIVATE, null);*/
           /* myDB.openDatabase("my.db", null, context.MODE_PRIVATE);*/
            myDB.execSQL(
                    "CREATE TABLE IF NOT EXISTS object (title VARCHAR(200),addr  VARCHAR(200), rent_date INT, price INT)"
            );
            ContentValues row1 = new ContentValues();
            row1.put("title", "Alice");
            row1.put("addr", "25");
            row1.put("rent_date", 1);
            row1.put("price", 1);

            myDB.insert("object", null, row1);


        } catch (Exception e) {
            Log.i("myerrorDataSaver", e.getMessage());
        }
    }

    public void getData() {
        try {
          /*  checkPermissions();*/

            Cursor myCursor =
                    myDB.rawQuery("select title, addr, rent_date from object", null);

            while (myCursor.moveToNext()) {
                String title = myCursor.getString(0);
                String addr = myCursor.getString(1);
                int rent_date = myCursor.getInt(2);
                Log.i("infoDataSaver:", title);
               /* boolean isSingle = (myCursor.getInt(2)) == 1 ? true : false;*/
                myCursor.close();

            }
        } catch (Exception e) {
        }
    }
}
