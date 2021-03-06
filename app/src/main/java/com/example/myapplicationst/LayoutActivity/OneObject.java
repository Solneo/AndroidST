package com.example.myapplicationst.LayoutActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Main.MainActivity;
import com.example.myapplicationst.Main.ThemeUtils;
import com.example.myapplicationst.NetCommunication.Adapters.AdapterForOneObj;
import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.FrInerf;
import com.example.myapplicationst.NetCommunication.Models.ModelOneObj;
import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.example.myapplicationst.R;
import com.example.myapplicationst.Slider.Slider;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public class OneObject extends FragmentActivity implements FrInerf {
    RecyclerView recyclerView;
    List<ModelOneObj> post;
    private ViewPager mPager;
    Slider slider;
    Images[] oneObjImg;
    ViewPager pager;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_object);
        ThemeUtils.onActivityCreateSetTheme(this);
        /*HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        Message msg = new Message();
        msg.obj = "Ali send message";
        handler.sendMessage(msg);*/
            startResponse();
       /* ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit()*/

    }

    @Override
    public void toRelate(Images[] imagesArray) {
        this.oneObjImg = imagesArray;
        setSlider();
    }

    public void setSlider() {
        mPager = (ViewPager) findViewById(R.id.pager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        Context context = OneObject.this;
        slider = new Slider();
        slider.init(mPager, indicator, context, oneObjImg);
    }


    public void onBackPressedButton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startResponse() {
        post = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_oneobj);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AdapterForOneObj adapt = new AdapterForOneObj(post, this);
        recyclerView.setAdapter(adapt);

        AppNetCom.getApi().getDat(AppNetCom.getStringId()).enqueue(new Callback<List<ModelOneObj>>() {


            @Override
            public void onResponse(Call<List<ModelOneObj>> call, Response<List<ModelOneObj>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            post.addAll(response.body());
                            recyclerView.getAdapter().notifyDataSetChanged();

                        } else {
                            okhttp3.Request request;
                            request = call.request();
                            Log.i("qwe", request.toString());
                        }
                    } else {
                        Log.i("qwe", response.message());
                    }
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ModelOneObj>> call, Throwable t) {
                Toast.makeText(OneObject.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }

        });
    }

/*

    private class MyHandlerThread extends HandlerThread {

        Handler handler;

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        protected void onLooperPrepared() {
            handler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    // это отдельный поток, вне графического интерфейса,
                    // для решения последовательного вызова не подошел
                }
            };
        }
    }*/
}
