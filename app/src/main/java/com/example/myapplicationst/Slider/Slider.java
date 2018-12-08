package com.example.myapplicationst.Slider;

import android.content.Context;
import android.os.Handler;

import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;

import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Ыщвф on 21.10.2018.
 */

public class Slider {
    private static ViewPager mPager;
    private static int currentPage = 0;
    /*  private static  Integer[] Arr = {R.drawable.ic_menu_share,
              R.drawable.ic_menu_send,
              R.drawable.ic_home_black_24dp};*/
  /*  private ArrayList<Integer> sliderArr = new ArrayList<Integer>();*/

    public Slider() {
    }

    /*public void setArr(Integer[] arr){
        this.Arr = arr;
    }*/
    public void init(ViewPager viewPager, CircleIndicator circleIndicator, Context context, Images[] imgArray) {
       /* for (int i = 0; i <Arr.length; i++)
            sliderArr.add(Arr[i]);*/
        final Images[] imgg = imgArray;
        mPager = viewPager;
        mPager.setAdapter(new PageAdapter(context, imgg));
        CircleIndicator indicator = circleIndicator;
        /*  indicator.setViewPager(mPager);*/

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imgg.length) {
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
}
