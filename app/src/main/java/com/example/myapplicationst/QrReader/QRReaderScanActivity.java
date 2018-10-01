package com.example.myapplicationst.QrReader;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.myapplicationst.R;

/**
 * Created by Ыщвф on 01.10.2018.
 */

public class QRReaderScanActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrreader_layout);
    }
    QRReaderActivity qrReaderActivity = new QRReaderActivity();

}
