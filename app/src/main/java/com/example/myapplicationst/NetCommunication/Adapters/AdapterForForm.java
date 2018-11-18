package com.example.myapplicationst.NetCommunication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.ProviderToGlob;
import com.example.myapplicationst.NetCommunication.Models.ModelForm;
import com.example.myapplicationst.R;

import java.util.List;

/**
 * Created by Ыщвф on 07.11.2018.
 */

public class AdapterForForm extends RecyclerView.Adapter<AdapterForForm.ViewHolder> implements ProviderToGlob {
    private List<ModelForm> posts;
    private Context context;
    private static ProviderToGlob providerToGlob;


    public AdapterForForm(List<ModelForm> posts, Context context, ProviderToGlob providerToGlob) {
        this.posts = posts;
        this.context = context;
        this.providerToGlob = providerToGlob;

    }

    @Override
    public void provideGlob(String s) {
        Log.d("errr", "0");
    }

    @NonNull
    @Override
    public AdapterForForm.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_list_object, parent, false);
        v.setVisibility(View.VISIBLE);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForForm.ViewHolder holder, int position) {
        ModelForm postModel = posts.get(position);

        holder.setIsRecyclable(false);
        String title = postModel.getTitle();
        String name = postModel.getName();
        String type = postModel.getType();
        String value = "0";
        String orig = "form_build_id";
        Boolean b = false;
        b = orig.equals(name);
        if (b) {
            value = postModel.getValue();
            holder.post.setText(postModel.getValue());
            providerToGlob.provideGlob(value);
        } else {
            Log.i(orig, type);
            Log.i("myerr", b.toString());
        }
        holder.post.setText(value);
        holder.site.setText(postModel.getTitle());


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
        TextView sdacha;
        TextView addr;
        String str;


        public ViewHolder(View v) {
            super(v);

            str = " ";
            post = (TextView) v.findViewById(R.id.textView_post);
            site = (TextView) v.findViewById(R.id.textView_site);
            prev = (ImageView) v.findViewById(R.id.image_pre);
            sdacha = (TextView) v.findViewById(R.id.sdacha);
            addr = (TextView) v.findViewById(R.id.addr);


        }
    }

}
