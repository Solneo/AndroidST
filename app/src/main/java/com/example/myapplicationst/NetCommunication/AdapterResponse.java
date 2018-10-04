package com.example.myapplicationst.NetCommunication;
import android.os.Build;
import android.view.View;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ыщвф on 03.10.2018.
 */

public class AdapterResponse extends RecyclerView.Adapter<AdapterResponse.ViewHolder>{
    private List<PostModel> posts;
    public AdapterResponse(List<PostModel> posts){
        this.posts = posts;
    }
    @NonNull
    @Override
    public AdapterResponse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResponse.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView post;
        TextView site;
        public ViewHolder(View v){
            super(v);

        }
    }

}
