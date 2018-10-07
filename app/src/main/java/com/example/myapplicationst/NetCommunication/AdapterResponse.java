package com.example.myapplicationst.NetCommunication;

import android.os.Build;
import android.view.View;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplicationst.R;

import java.util.List;

/**
 * Created by Ыщвф on 03.10.2018.
 */

public class AdapterResponse extends RecyclerView.Adapter<AdapterResponse.ViewHolder> {
    private List<PostModel> posts;

    public AdapterResponse(List<PostModel> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public AdapterResponse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_objekt_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResponse.ViewHolder holder, int position) {

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

        public ViewHolder(View v) {
            super(v);
            post = (TextView) v.findViewById(R.id.textView_post);
            site = (TextView) v.findViewById(R.id.textView_site);

        }
    }

}
