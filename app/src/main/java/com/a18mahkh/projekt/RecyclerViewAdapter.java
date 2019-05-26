package com.a18mahkh.projekt;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;


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

        //VoteFragment voteFragment = VoteFragment.newInstance(listItem.getMovie_title(), listItem.getCategory());

        Picasso.with(context)
                .load(listItem.getImgUrl())
                .into(viewHolder.banner_image);

        viewHolder.banner_image.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){

                //context.startActivity(new Intent(context, MovieDetails.class));

                String movieDes = listItem.info();
                String movieTitle= listItem.getMovie_title();
                String movieUrl = listItem.getImgUrl();

                Intent intent = new Intent(context.getApplicationContext(), MovieDetails.class);



                intent.putExtra("movieBannerImg", movieUrl);
                intent.putExtra("movieTitle", movieTitle);
                intent.putExtra("movieInfo", movieDes);

                context.startActivity(intent);

            }
        });
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

       ImageView banner_image;
       TextView title_textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_textView = itemView.findViewById(R.id.name_view);
           // desc_textView=itemView.findViewById(R.id.desc_textView);
            banner_image=itemView.findViewById(R.id.image_view);
        }
    }
}
