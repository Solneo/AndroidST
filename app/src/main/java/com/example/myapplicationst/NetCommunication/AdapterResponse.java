package com.example.myapplicationst.NetCommunication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationst.AppNetCom;
import com.example.myapplicationst.OneObject;
import com.example.myapplicationst.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ыщвф on 03.10.2018.
 */

public class AdapterResponse extends RecyclerView.Adapter<AdapterResponse.ViewHolder> implements RecyclerViewClickListener {
    private List<PostModel> posts;
    private List<Images> img;
    private Context context;
    private static RecyclerViewClickListener itemListener;

    public AdapterResponse(List<PostModel> posts,Context context, RecyclerViewClickListener itemListener) {
        this.posts = posts;
        this.context = context;
        this.itemListener = itemListener;
    }

    public void recyclerViewListClicked(View v, int position, String str) {
        Log.d("errrrrrr",
                "0");
    }
    @NonNull
    @Override
    public AdapterResponse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        v.setVisibility(View.VISIBLE);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResponse.ViewHolder holder, int position) {
        PostModel postModel = posts.get(position);

        holder.setIsRecyclable(false);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        }else {
            holder.post.setText(Html.fromHtml(postModel.getElementPureHtml()));
        }*/
        holder.addr.setText(postModel.getAddr());
        holder.sdacha.setText("Сдача в " + postModel.getRent_date());
        holder.post.setText(postModel.getPrice() + " руб/м. кв.");
        holder.site.setText(postModel.getTitle());
        holder.str = postModel.getobjId();
        holder.proxystr.setText(postModel.getobjId());
        /*Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.prev);*/
       /*  Picasso.get().load(postModel.getImage()).into(holder.prev);*/

    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView post;
        TextView site;
        ImageView prev;
        TextView sdacha;
        TextView addr;
        TextView proxystr;
        String str;

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), str);
        }

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            str = " ";
            post = (TextView) v.findViewById(R.id.textView_post);
            site = (TextView) v.findViewById(R.id.textView_site);
            prev = (ImageView) v.findViewById(R.id.image_pre);
            sdacha = (TextView) v.findViewById(R.id.sdacha);
            addr = (TextView) v.findViewById(R.id.addr);
            proxystr = (TextView) v.findViewById(R.id.proxy_str);
        }
    }

}
/*class QWE extends Activity{
    public static String string;
    static void  setString(String st){
        string = st;
    }
    void qe(String st){
        AppNetCom appNetCom = (AppNetCom) getApplication();
        ((AppNetCom) getApplication()).setStringId(st);
    }
}*/
