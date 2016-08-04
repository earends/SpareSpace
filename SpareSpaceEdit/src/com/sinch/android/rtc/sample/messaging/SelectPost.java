package com.sinch.android.rtc.sample.messaging;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SelectPost extends Activity {
    private TextView title;
    private TextView description;
    private TextView location;
    private TextView cost;
    private TextView dimmension;
    private TextView phone;
    private TextView email;
    private ImageView im;
    private ImageView im2;
    private boolean which;
    private boolean which2;
    private static final String SERVER_ADDRESS = "http://sparespace.netai.net/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_post);
        which = false;
        which2 = false;
        title = (TextView) findViewById(R.id.textView10);
        description = (TextView) findViewById(R.id.textView15);
        location = (TextView) findViewById(R.id.textView16);
        cost = (TextView) findViewById(R.id.textView17);
        dimmension = (TextView) findViewById(R.id.textView18);
        phone = (TextView) findViewById(R.id.textView19);
        email = (TextView) findViewById(R.id.textView20);
        im = (ImageView) findViewById(R.id.imageView3);
        im2 = (ImageView) findViewById(R.id.imageView4);


        title.setText(home.title);
        description.setText(home.description);
        cost.setText(home.cost);
        dimmension.setText(home.dimmension);
        phone.setText(home.phone);
        email.setText(home.email);
        if (!home.img.equals("null")) {
            //which = true;
            new DownloadImage(home.img, im).execute();
        }

        if (!home.img2.equals("null")) {
            //which2 = true;
            new DownloadImage(home.img2, im2).execute();
        }


    }

    public void back_click(View v) {
        Intent i = new Intent(getApplicationContext(),Home_page.class);
        startActivity(i);
    }

    private class DownloadImage extends AsyncTask<Void,Void,Bitmap> {
        String name;
        ImageView view;


        public DownloadImage(String name, ImageView image) {
            Log.d("downloading", "constructor");
            this.name = name;
            this.view = image;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String url = SERVER_ADDRESS + "pictures/" + name + ".jpg";
            try {
                Log.d("downloading", "try");
                Log.d("downloading", url);
                URLConnection connection = new URL(url).openConnection();
                connection.setConnectTimeout(1000*30);
                connection.setReadTimeout(1000*30);
                return BitmapFactory.decodeStream((InputStream)connection.getContent(),null,null);

            } catch( Exception e) {
                Log.d("downloading", "catch");
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                view.setImageBitmap(bitmap);
                //download_im.setImageBitmap(bitmap);
            }
        }
    }


    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000* 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000* 30);
        return httpRequestParams;

    }
}
