package com.example.myapplicationst.QrReader;

import android.Manifest;
import android.app.Activity;
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
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/* Import ZBar Class files */
import com.example.myapplicationst.R;

import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import net.sourceforge.zbar.Config;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;


/**
 * Created by Ыщвф on 30.09.2018.
 */

public class QRReaderActivity extends Activity {
    private static final String cameraPerm = Manifest.permission.CAMERA;
    private Camera mCamera;
   // private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private FrameLayout preview;
    private TextView scanText;
    private ImageView bar_code;
    private EditText code_for_bar;
    private ImageScanner scanner;
    private boolean barcodeScanned = false;
    private boolean previewing = true;
    private String lastScannedCode;
    private Image codeImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrreader_layout);


    }
}