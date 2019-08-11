package com.example.sanjaymurugan.sojournhappy;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.xmlpull.v1.XmlPullParser;

//import static com.softwares.rajkumar.sojournhappy.R.layout.activity_home;
//import static com.softwares.rajkumar.sojournhappy.R.layout.imagesview;

/**
 * Created by RAJKUMAR on 03-03-2018.
 */

public class custompageadapter extends PagerAdapter {
    Context mcontext;
//    LayoutInflater layoutInflater;
custompageadapter(Context context){
        mcontext=context;
    }
//    int noofimg=3;
    int[] image=new int[]{R.mipmap.qwert,R.mipmap.busstandnew,R.mipmap.busstandold};
    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(image[position]);
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
