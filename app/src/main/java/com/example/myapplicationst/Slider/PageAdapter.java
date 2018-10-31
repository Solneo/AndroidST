package com.example.myapplicationst.Slider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.example.myapplicationst.R;
import com.squareup.picasso.Picasso;

public class PageAdapter extends PagerAdapter {

  /*  private ArrayList<Integer> images;*/
    private Images[] imgAr;
    private LayoutInflater inflater;
    private Context context;

    public PageAdapter(Context context,/* ArrayList<Integer> images,*/Images[] imgArr) {
        this.context = context;
      /*  this.images=images;*/
        inflater = LayoutInflater.from(context);
        this.imgAr = imgArr;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        /*return images.size();*/
        return imgAr.length;
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        View myImageLayout = inflater.inflate(R.layout.fragment, viewGroup, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
       /* myImage.setImageResource(images.get(position));*/
        Picasso.get().load(imgAr[position].getSrc()).into(myImage);
        viewGroup.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}