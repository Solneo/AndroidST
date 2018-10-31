package com.example.myapplicationst.NetCommunication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.example.myapplicationst.NetCommunication.Models.ModelPostAsk;
import com.example.myapplicationst.R;

import java.util.List;

/**
 * Created by Ыщвф on 25.10.2018.
 */

public class AdapterForYoken extends RecyclerView.Adapter<AdapterForYoken.ViewHolder>{
    private List<ModelPostAsk> posts;
    private Context context;

    public AdapterForYoken(List<ModelPostAsk> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterForYoken.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_create_obj, parent, false);
        v.setVisibility(View.VISIBLE);
        return new AdapterForYoken.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForYoken.ViewHolder holder, int position) {
        ModelPostAsk modelPostAsk = posts.get(position);

        holder.setIsRecyclable(false);
       /*
        holder.addr.setText(postModel.getAddr());
        holder.sdacha.setText("Сдача в " + postModel.getRent_date());
        holder.post.setText(postModel.getPrice() + " руб/м. кв.");
        holder.site.setText(postModel.getTitle());
        holder.str = postModel.getobjId();
*/
        holder.post.setText("Логин: "+modelPostAsk.getLogin());
        holder.site.setText("Пароль: "+modelPostAsk.getPassword());

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
            post = (TextView) v.findViewById(R.id.login_t);
            site = (TextView) v.findViewById(R.id.pswd_t);


        }
    }

}