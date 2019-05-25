package com.a18mahkh.projekt;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.media.ImageWriter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// 2. Create a new activity named "MountainDetailsActivity
public class MountainDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountaindetails);

        /*Intent intent = getIntent();
        String mountainDetails=intent.getStringExtra("mountainDetails");

        if (mountainDetails != null) {

            TextView textView = (TextView) findViewById(R.id.about_textView);
            textView.setText(mountainDetails);



        }*/
        TextView textView = (TextView) findViewById(R.id.about_textView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        }

        /*Button goBackBtn=findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                finish();
            }
        });*/

    }

}
