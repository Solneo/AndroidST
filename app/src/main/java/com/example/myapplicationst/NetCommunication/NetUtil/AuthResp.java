package com.example.myapplicationst.NetCommunication.NetUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.NetCommunication.Models.ModelAuth;
import com.example.myapplicationst.NetCommunication.Models.SubModels.RecuestBody;

import java.net.CookieManager;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplicationst.NetCommunication.NetUtil.RequestMultiBody.createPartFromString;

/**
 * Created by Ыщвф on 11.11.2018.
 */

public class AuthResp {
    String sessid;
    String session_name;
    Context context;


    public AuthResp(Context context) {
        this.context = context;
    }

    private static int qwe(int i) {
        return i;
    }

    public int startResponseAuth(String loginG, String passwordG) {
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




        /*RequestBody user_id = params.put("user_id", userId);*/
        AppNetCom.getApi().startAuth(params).enqueue(new Callback<ModelAuth>() {
            @Override
            public void onResponse(Call<ModelAuth> call, Response<ModelAuth> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {

                        session_name = response.body().getSession_name();
                        sessid = response.body().getSessid();
                        AppNetCom.setStringCookie(session_name + "=" + sessid);
                        AppNetCom.setStringToken(response.body().getToken());

                    } else {
                        Log.i("qwert", response.message());

                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwerty", request.toString());

                }
            }

            @Override
            public void onFailure(Call<ModelAuth> call, Throwable t) {
                Toast.makeText(context, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
                qwe(1);
            }
        });
        return 0;
    }
}
