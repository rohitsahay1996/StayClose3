package com.example.rohit.stayclose;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {
    private ImageView mAboutImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mAboutImg = (ImageView) findViewById(R.id.about_img);

        mAboutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about_intent = new Intent(AboutActivity.this,SecondActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair(mAboutImg , "image_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AboutActivity.this, pairs);

                startActivity(about_intent, options.toBundle());
            }
        });


    }
}
