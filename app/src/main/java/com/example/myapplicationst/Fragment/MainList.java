package com.example.myapplicationst.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplicationst.R;

import androidx.annotation.Nullable;

/**
 * Created by Ыщвф on 28.09.2018.
 */

public class MainList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_list, container, false);
    }
}
