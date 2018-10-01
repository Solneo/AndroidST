package com.example.myapplicationst.QrReader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.hardware.camera2.*;

/* Import ZBar Class files */
import com.example.myapplicationst.R;
import com.google.android.gms.common.logging.Logger;
import com.google.zxing.Result;
import android.provider.SyncStateContract.Constants;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import net.sourceforge.zbar.Config;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by Ыщвф on 30.09.2018.
 */

public class QRReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

        private ZXingScannerView mScannerView;

        @Override
        public void onCreate(Bundle state) {
            super.onCreate(state);
            // Programmatically initialize the scanner view
            mScannerView = new ZXingScannerView(this);
            // Set the scanner view as the content view
            setContentView(mScannerView);
        }

        @Override
        public void onResume() {
            super.onResume();
            // Register ourselves as a handler for scan results.
            mScannerView.setResultHandler(this);
            // Start camera on resume
            mScannerView.startCamera();
        }

        @Override
        public void onPause() {
            super.onPause();
            // Stop camera on pause
            mScannerView.stopCamera();
        }

        @Override
        public void handleResult(Result rawResult) {
            // Do something with the result here
            // Prints scan results
           // Logger.verbose("result", rawResult.getText());
            // Prints the scan format (qrcode, pdf417 etc.)
           // Logger.verbose("result", rawResult.getBarcodeFormat().toString());
            //If you would like to resume scanning, call this method below:
            //mScannerView.resumeCameraPreview(this);
            Intent intent = new Intent();
           // intent.putExtra(AppConstants.KEY_QR_CODE, rawResult.getText());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
