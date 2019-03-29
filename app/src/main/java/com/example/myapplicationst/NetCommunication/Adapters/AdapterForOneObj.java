package com.example.myapplicationst.NetCommunication.Adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.FrInerf;
import com.example.myapplicationst.NetCommunication.Models.ModelOneObj;
import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.example.myapplicationst.R;
import com.example.myapplicationst.Slider.Slider;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public class AdapterForOneObj extends RecyclerView.Adapter<AdapterForOneObj.ViewHolderForOneObj> implements FrInerf {
    private List<ModelOneObj> posts;
    private Images[] img;
    private Slider slider;
    private static FrInerf frInerf;

    public AdapterForOneObj(List<ModelOneObj> posts,
                            FrInerf frInerf) {
        this.posts = posts;
        this.frInerf = frInerf;
    }

    @NonNull
    @Override
    public AdapterForOneObj.ViewHolderForOneObj onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_one_object, parent, false);
        v.setVisibility(View.VISIBLE);
        return new AdapterForOneObj.ViewHolderForOneObj(v);
    }

    @Override
    public void toRelate(Images[] imagesArray) {
        Log.i("err", "12594");
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForOneObj.ViewHolderForOneObj holder, int position) {
        ModelOneObj modelOneObj = posts.get(position);

        holder.setIsRecyclable(false);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        }else {
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml()));
        }*/
        holder.addr.setText(modelOneObj.getAddr());
        holder.sdacha.setText("Сдача в " + modelOneObj.getRent_date());
        holder.post.setText("Цена  "+modelOneObj.getPrice() + " руб/м. кв.");
        holder.site.setText(modelOneObj.getTitleObj());
        holder.bigText.setText(modelOneObj.getBigText());
        frInerf.toRelate(modelOneObj.getImagesList());
        int p = modelOneObj.getImagesList().length;
        String t = Integer.toString(p);
        Log.i("osh",t);
        /*Images[] imgList = postModel.getImagesList();
        for (Images img: imgList) {
            Picasso.get().load(img.getSrc()).into(holder.prev);
        }*/
        /*Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.prev);*/
     /*  Picasso.get().load(postModel.getImage()).into(holder.prev);*/
        /*holder.vpNews.cr*/
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolderForOneObj extends RecyclerView.ViewHolder {
        TextView post;
        TextView site;
        ImageView prev;
        TextView sdacha;
        TextView addr;
        TextView bigText;
       /* ViewPager vpNews;*/

        public ViewHolderForOneObj(View v) {
            super(v);

            post = (TextView) v.findViewById(R.id.textView_price);
            site = (TextView) v.findViewById(R.id.textView_gk);
            sdacha = (TextView) v.findViewById(R.id.sdacha_gk);
            addr = (TextView) v.findViewById(R.id.addr_inObj);
            bigText = (TextView) v.findViewById(R.id.bigText);

           /* vpNews = (ViewPager) v.findViewById(R.id.viewPagerForImage);*/
        }
    }

}