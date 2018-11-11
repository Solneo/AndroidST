package com.example.myapplicationst.LayoutActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationst.QrReader.BARReader;
import com.example.myapplicationst.R;

public class LoginActivity extends Activity {

    private EditText nameView;
    private EditText passwordView;
    private TextView regView;
    private Button signButton;
    private View mProgressView;
    private View mLoginFormView;
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameView = (EditText) findViewById(R.id.name);
        passwordView = (EditText) findViewById(R.id.password);
        regView = (TextView) findViewById(R.id.text_reg);
        signButton = (Button) findViewById(R.id.sign_in_button);

        regView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStart();
            }
        });

    }

    public void loginStart() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        signButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String name = nameView.getText().toString();
        String password = passwordView.getText().toString();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically нормально разобраться с этим
                this.finish();
            }
        }
    }

    public boolean validate() {
        return false;
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        signButton.setEnabled(true);
    }

    public void goToReg(View v) {

    }

    public void goToMyAccount(View v) {

    }

    public void goToQRReader(View v) {
        Intent intent = new Intent(this, BARReader.class);
        startActivity(intent);
    }
}