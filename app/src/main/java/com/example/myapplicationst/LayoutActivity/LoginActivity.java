package com.example.myapplicationst.LayoutActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.NetCommunication.Models.ModelAuth;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;
import com.example.myapplicationst.NetCommunication.Models.SubModels.User;
import com.example.myapplicationst.NetCommunication.NetUtil.RequestMultiBody;
import com.example.myapplicationst.QrReader.BARReader;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.PrefSaver;

import java.net.CookieManager;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplicationst.NetCommunication.NetUtil.RequestMultiBody.createPartFromString;

public class LoginActivity extends Activity {
    private String sessid;
    private String session_name;
    private EditText nameView;
    private EditText passwordView;
    private TextView regView;
    private Button signButton;
    private View mProgressView;
    private View mLoginFormView;
    private static final int REQUEST_SIGNUP = 0;
    private ProgressDialog progressDialog;

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
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
    }


    public void loginStart() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        signButton.setEnabled(false);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String name = nameView.getText().toString();
        String password = passwordView.getText().toString();

        startResponseAuth(name, password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean validate() {
        boolean valid;
        valid = true;
        return valid;
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        signButton.setEnabled(true);
    }

    public void onLoginSuccess() {
        finish();
    }

    public void goToReg(View v) {

    }

    public void goToMyAccount(View v) {

    }

    public void goToQRReader(View v) {
        Intent intent = new Intent(this, BARReader.class);
        startActivity(intent);
    }

    public void startResponseAuth(String loginG, String passwordG) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.getCookieStore().removeAll();
        final RecuestBody recuestBody = new RecuestBody();
        String LOGIN = loginG;
        String PASSWORD = passwordG;

        RequestMultiBody requestBody = new RequestMultiBody();

        RequestBody username = createPartFromString(LOGIN);
        RequestBody password = createPartFromString(PASSWORD);

        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        final PrefSaver prefSaver = new PrefSaver(this);

        AppNetCom.getApi().startAuth(params).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        prefSaver.saveData("auth", "0");
                        session_name = response.body().getSession_name();
                        sessid = response.body().getSessid();
                        prefSaver.saveData("token", response.body().getToken());
                        prefSaver.saveData("cookie", session_name + "=" + sessid);
                        AppNetCom.setStringCookie(session_name + "=" + sessid);
                        AppNetCom.setStringToken(response.body().getToken());

                        User user = response.body().getUser();
                        prefSaver.saveData("username", user.getName());
                        prefSaver.saveData("email", user.getMail());
                        onLoginSuccess();
                        progressDialog.dismiss();

                    } else {
                        Log.i("qwert", response.message());
                        onLoginFailed();
                        progressDialog.dismiss();
                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwerty", request.toString());
                    onLoginFailed();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ModelAuth> call, Throwable t) {
                /*Toast.makeText(context, "Чет, поломалось...", Toast.LENGTH_SHORT).show();*/
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
                onLoginFailed();
                progressDialog.dismiss();
            }
        });
    }
}