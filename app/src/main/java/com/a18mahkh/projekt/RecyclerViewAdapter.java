package com.a18mahkh.projekt;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Movie_itemlist> listItems;
    private Context context;

    public RecyclerViewAdapter(List<Movie_itemlist> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trending_imagelist, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Movie_itemlist listItem=listItems.get(position);


        viewHolder.title_textView.setText(listItem.getMovie_title());
        viewHolder.desc_textView.setText(listItem.getMovie_description());

        Picasso.with(context)
                .load(listItem.getImgUrl())
                .into(viewHolder.banner_image);
        /*Glide.with(context)
                .asBitmap()
                .load(listItem.getImgUrl())
                .into(viewHolder.banner_image);*/



    }


    @Override
    public int getItemCount() {
        return listItems.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        //CircleImageView banner_image;
        TextView title_textView;
        TextView desc_textView;
        ImageView banner_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_textView = itemView.findViewById(R.id.title_textView);
            desc_textView=itemView.findViewById(R.id.desc_textView);
            banner_image=itemView.findViewById(R.id.img_imageView);
        }
    }
}
