package com.example.myapplicationst.Main;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.myapplicationst.App.AppNetCom;

/**
 * Created by Ыщвф on 19.11.2018.
 */

public class ThemeChUt {
    public void setMyThemeToRec(RecyclerView recyclerView) {
        String th = AppNetCom.getMyTheme();
        switch (th) {
            case "Dark":
                recyclerView.setBackgroundColor(0xFF202020);
                break;
            default:
                recyclerView.setBackgroundColor(0xFFC0C0C0);
                break;
        }
    }

    public void setMyThemeToRecItem(android.support.v7.widget.CardView v) {
        String th = AppNetCom.getMyTheme();
        switch (th) {
            case "Dark":
                v.setCardBackgroundColor(0xFF323232);
                break;
            default:
                v.setCardBackgroundColor(0xFFC0C0C0);
                break;
        }
    }

    public void setMyThemeToTextView(TextView textView) {
        String th = AppNetCom.getMyTheme();
        switch (th) {
            case "Dark":
                textView.setTextColor(0xFFB0B0B0);
                break;
            default:
                textView.setTextColor(0xFF101010);
                break;
        }
    }

    public void setMyThemeToTextView(TextView textView, String title) {
        String th = AppNetCom.getMyTheme();
        switch (th) {
            case "Dark":
                textView.setTextColor(0xFFFFFFFF);
                break;
            default:
                textView.setTextColor(0xFF101010);
                break;
        }
    }
}
