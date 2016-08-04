package com.sinch.android.rtc.sample.messaging;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//class borrwed from https://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html

/**
 * Class creates a custom list view so that the Home page list view can contain images as well as postings
 * YET TO INCLUDE - ON CLICK LISTENER FOR EACH POSTING
 * Figure out how to assign id to each posting
 */
public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    //private final String[] web;
    private final ArrayList<String> web;
    //private final Integer[] imageId;
    private final ArrayList<Bitmap> bitbug;
    public CustomList(Activity context, ArrayList<String> web, ArrayList<Bitmap> bitBug) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        //this.imageId = imageId;
        this.bitbug = bitBug;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web.get(position));
        txtTitle.setTextSize(25);
        txtTitle.setTextColor(Color.BLACK);
        //mImg.setImageBitmap(bmOut);
        //imageView.setImageResource(imageId[position]);
        imageView.setImageBitmap(bitbug.get(position));
        return rowView;
    }
}
