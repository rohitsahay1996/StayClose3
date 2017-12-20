package com.example.rohit.stayclose;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Rohit on 12/17/2017.
 */

class SlideAdapter extends PagerAdapter {

Context context;
    LayoutInflater layoutInflater;


    public SlideAdapter(Context context){ this.context = context; }

    //Arrays

    public int[] slide_images = {

            R.drawable.logo,
            R.drawable.calling,
            R.drawable.videocalling_icon
    };

    public String[] slide_headings = {

            "Stay Close",
            "Free Voice Calling",
            "Free Video Calling"
    };

    public String[] slide_desc = {

            "Stay Close is the only place where you will find all the free video and voice calling application for android mobile phones" +
                    "--Stay Close",
            "In stay close you will find all the android mobile application from which you can call globally and Free of cost" +
                    "--Stay Close",
            "In stay close you will find all the android mobile application from which you can do video calls globally and Free of cost" +
                    "--Stay Close"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDesc = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
