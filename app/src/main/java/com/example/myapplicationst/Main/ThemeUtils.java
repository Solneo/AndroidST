package com.example.myapplicationst.Main;

import android.app.Activity;
import android.content.Intent;

import com.example.myapplicationst.R;

/**
 * Created by Ыщвф on 01.11.2018.
 */

public class ThemeUtils {
    private static int sTheme;

    public final static int FIRE_BRICK = 0;
    public final static int DODGER_BLUE = 1;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
                activity.setTheme(R.style.AppTheme);
                break;
            case FIRE_BRICK:
                activity.setTheme(R.style.Theme_AppCompat_Light);
                break;
            case DODGER_BLUE:
                activity.setTheme(R.style.Theme_Design);
                break;
        }
    }
}
