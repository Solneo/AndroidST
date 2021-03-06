package com.example.myapplicationst.NetCommunication.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.Main.ThemeChUt;
import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.RecyclerViewClickListener;
import com.example.myapplicationst.NetCommunication.Models.PostModel;
import com.example.myapplicationst.NetCommunication.Models.SubModels.Images;
import com.example.myapplicationst.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ыщвф on 03.10.2018.
 */

public class AdapterResponse extends RecyclerView.Adapter<AdapterResponse.ViewHolder> implements RecyclerViewClickListener {
    private List<PostModel> posts;
    private List<Images> img;
    private Context context;
    private static RecyclerViewClickListener itemListener;
    ThemeChUt themeChUt;

    public AdapterResponse(List<PostModel> posts, Context context, RecyclerViewClickListener itemListener) {
        this.posts = posts;
        this.context = context;
        this.itemListener = itemListener;
        themeChUt = new ThemeChUt();
    }

    public void recyclerViewListClicked(View v, int position, String str) {
        Log.d("errr",
                "0");
    }

    @NonNull
    @Override
    public AdapterResponse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_list_object, parent, false);
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
        holder.post.setText(postModel.getPrice() + " \u20BD");
        holder.site.setText(postModel.getTitle());
        holder.str = postModel.getobjId();

        /*Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.prev);*/
        Picasso.get().load(postModel.getImage()).into(holder.prev);

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
        CardView card;
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
            card = (CardView) v.findViewById(R.id.card_view);

            themeChUt.setMyThemeToRecItem(card);
            themeChUt.setMyThemeToTextView(addr);
            themeChUt.setMyThemeToTextView(sdacha);
            themeChUt.setMyThemeToTextView(post, "");
            themeChUt.setMyThemeToTextView(site, "");

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
