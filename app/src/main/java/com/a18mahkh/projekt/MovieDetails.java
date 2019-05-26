package com.a18mahkh.projekt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import static android.content.Intent.getIntent;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String movieBannerImg = intent.getStringExtra("movieBannerImg");
        String movieTitle=intent.getStringExtra("movieTitle");
        String movieDes=intent.getStringExtra("movieInfo");




        TextView movie_detailTitle = findViewById(R.id.movie_detailTitle);
        movie_detailTitle.setText(movieTitle);

        TextView movie_details = (TextView) findViewById(R.id.movie_details_text);
        movie_details.setText(movieDes);

       ImageView movie_detailBanner = findViewById(R.id.movie_detailBanner);
       Glide.with(this)
                .asBitmap()
                .load(movieBannerImg)
                .into(movie_detailBanner);



    }
}
