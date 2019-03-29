package com.example.myapplicationst.Main;


import android.widget.TextView;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ыщвф on 19.11.2018.
 */

public class ThemeChUt {
    private int PRIMARY_DARK = 0xFF263238;
    private int PRIMARY_DARK_SHADOW = 0xFF131619;
    private int PRIMARY_DARK_TEXT = 0xFFFFFFFF;
    private int SECONDARY_DARK_TEXT = 0xFFcfd8dc;

    private int PRIMARY_LIGHT = 0xFFf0f0f0;
    private int PRIMARY_LIGHT_SHADOW = 0xFFcfd8dc;
    private int PRIMARY_LIGHT_TEXT = 0xFF212121;
    private int SECONDARY_LIGHT_TEXT = 0xFF555555;

    public void setMyThemeToRec(RecyclerView recyclerView) {
        int th = AppNetCom.getMyTheme();
        switch (th) {
            case R.style.myThemeDark:
                recyclerView.setBackgroundColor(PRIMARY_DARK_SHADOW);
                break;
            default:
                recyclerView.setBackgroundColor(PRIMARY_LIGHT_SHADOW);
                break;
        }
    }

    public void setMyThemeToRecItem(CardView v) {
        int th = AppNetCom.getMyTheme();
        switch (th) {
            case R.style.myThemeDark:
                v.setCardBackgroundColor(PRIMARY_DARK);
                break;
            default:
                v.setCardBackgroundColor(PRIMARY_LIGHT);
                break;
        }
    }

    public void setMyThemeToTextView(TextView textView) {
        int th = AppNetCom.getMyTheme();
        switch (th) {
            case R.style.myThemeDark:
                textView.setTextColor(SECONDARY_DARK_TEXT);
                break;
            default:
                textView.setTextColor(SECONDARY_LIGHT_TEXT);
                break;
        }
    }

    public void setMyThemeToTextView(TextView textView, String title) {
        int th = AppNetCom.getMyTheme();
        switch (th) {
            case R.style.myThemeDark:
                textView.setTextColor(PRIMARY_DARK_TEXT);
                break;
            default:
                textView.setTextColor(PRIMARY_LIGHT_TEXT);
                break;
        }
    }
}
