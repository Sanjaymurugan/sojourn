package com.example.sanjaymurugan.sojournhappy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by RAJKUMAR on 06-03-2018.
 */

public class baseadapter extends BaseAdapter {
    Context mcontext;
    String flag[];
    int textv[];
    int strong[];
//    Double lat[];
//    Double lon[];
//    TextView namecity,citytemperature,temperaturedescription;

    LayoutInflater layoutInflater;
    public baseadapter(Context context, String[] flag, int[] strong) {
        this.mcontext=context;
        this.flag=flag;
        this.textv=textv;
        this.strong=strong;
        layoutInflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return flag.length;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.childlist,null);
        ImageView  imageView=(ImageView)convertView.findViewById(R.id.childlist);
        Glide.with(mcontext).load(flag[position]).into(imageView);
        TextView nameofplaces=(TextView)convertView.findViewById(R.id.nameofplace);
//        namecity=(TextView)convertView.findViewById(R.id.placename);
//        citytemperature=(TextView)convertView.findViewById(R.id.placetemperature);
//        temperaturedescription=(TextView)convertView.findViewById(R.id.seasonaldescription);

//        imageView.setImageResource(flag[position]);
        nameofplaces.setText(strong[position]);
        return convertView;
    }


}
