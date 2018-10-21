package com.example.myapplicationst.NetCommunication;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ыщвф on 13.10.2018.
 */

public class AdapterForOneObj extends RecyclerView.Adapter<AdapterForOneObj.ViewHolderForOneObj> {
    private List<ModelOneObj> posts;
    private List<Images> img;

    public AdapterForOneObj(List<ModelOneObj> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public AdapterForOneObj.ViewHolderForOneObj onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_one_object, parent, false);
        v.setVisibility(View.VISIBLE);
        return new AdapterForOneObj.ViewHolderForOneObj(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForOneObj.ViewHolderForOneObj holder, int position) {
        ModelOneObj postModel = posts.get(position);

        holder.setIsRecyclable(false);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        }else {
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml()));
        }*/
        holder.addr.setText(postModel.getAddr());
        holder.sdacha.setText("Сдача в " + postModel.getRent_date());
        holder.post.setText(postModel.getPrice() + " руб/м. кв.");
        holder.site.setText(postModel.getTitleObj());
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
       /* ViewPager vpNews;*/

        public ViewHolderForOneObj(View v) {
            super(v);

            post = (TextView) v.findViewById(R.id.textView_price);
            site = (TextView) v.findViewById(R.id.textView_gk);
            sdacha = (TextView) v.findViewById(R.id.sdacha_gk);
            addr = (TextView) v.findViewById(R.id.addr_inObj);
           /* vpNews = (ViewPager) v.findViewById(R.id.viewPagerForImage);*/
        }
    }

}