package com.example.myapplicationst.NetCommunication;

import android.media.Image;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplicationst.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ыщвф on 03.10.2018.
 */

public class AdapterResponse extends RecyclerView.Adapter<AdapterResponse.ViewHolder> {
    private List<PostModel> posts;
    private List<Images> img;

    public AdapterResponse(List<PostModel> posts) {
        this.posts = posts;
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


      holder.post.setText(postModel.getPrice());
      holder.site.setText(postModel.getTitle());
        /*Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.prev);*/
        Picasso.get().load(postModel.getImage()).into(holder.prev);

    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView post;
        TextView site;
        ImageView prev;

        public ViewHolder(View v) {
            super(v);
            post = (TextView) v.findViewById(R.id.textView_post);
            site = (TextView) v.findViewById(R.id.textView_site);
            prev = (ImageView) v.findViewById(R.id.image_pre);
            /*site.setVerticalScrollbarPosition(1);
            post.setVerticalScrollbarPosition(2);*/

        }
    }

}
