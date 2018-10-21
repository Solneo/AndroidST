package com.example.myapplicationst;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationst.NetCommunication.AdapterForOneObj;
import com.example.myapplicationst.NetCommunication.ModelOneObj;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public class OneObject extends FragmentActivity {
    RecyclerView recyclerView;
    List<ModelOneObj> post;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.ic_menu_share,
            R.drawable.ic_menu_send,
            R.drawable.ic_home_black_24dp};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_object_activity);

        startResponse();
        init();
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PageAdapter(OneObject.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        /*Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);*/
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

        AdapterForOneObj adapt = new AdapterForOneObj(post);
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
}

