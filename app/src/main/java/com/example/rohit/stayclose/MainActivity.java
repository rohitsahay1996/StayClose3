package com.example.rohit.stayclose;

import android.content.Intent;
import android.support.v4.print.PrintHelper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SlideAdapter sliderAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mdotsLayout;
    private Button nxtBtn;
    private Button prevBtn;
    private TextView[] mdots;
    private int mCurrentPage;
    private TextView mSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.slide_view);
        mdotsLayout = (RelativeLayout) findViewById(R.id.dotsRelativeLayout);
        nxtBtn = (Button)findViewById(R.id.nxtBtn);
        prevBtn = (Button)findViewById(R.id.prevBtn);

        mSkip = (TextView) findViewById(R.id.skipText);

        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtosecond();

            }
        });
        sliderAdapter = new SlideAdapter(this);
        mViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);

        mViewPager.addOnPageChangeListener(viewListner);


        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCurrentPage+1);

            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCurrentPage-1);
            }
        });








    }

    private void addDotsIndicator(int position) {


        mdots= new TextView[3];
        mdotsLayout.removeAllViews();
        for(int i=0 ; i<mdots.length;i++){

            mdots[i] = new TextView(this);
            mdots[i].setText("Swipe->");
            mdots[i].setTextSize(12);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mdotsLayout.addView(mdots[i]);

        }

        if(mdots.length > 0){



            //mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
            mdots[position].setText("");
        }

    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mCurrentPage = position;

            if(position == 0){

                nxtBtn.setEnabled(true);
                prevBtn.setEnabled(false);
                nxtBtn.setVisibility(View.INVISIBLE);
                prevBtn.setVisibility(View.INVISIBLE);
                nxtBtn.setText("NEXT");
                prevBtn.setText("");
            }
            else if(position == mdots.length-1){

                nxtBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                nxtBtn.setVisibility(View.VISIBLE);
                prevBtn.setVisibility(View.VISIBLE);
                nxtBtn.setText( "FINISH");
                prevBtn.setText("BACK");

                nxtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendtosecond();
                    }
                });
            }
            else{

                nxtBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                nxtBtn.setVisibility(View.INVISIBLE);
                prevBtn.setVisibility(View.VISIBLE);
                nxtBtn.setText("NEXT");
                prevBtn.setText("BACK");
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void sendtosecond() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "welcome to Stay Close", Toast.LENGTH_LONG).show();
        finish();
    }
}
