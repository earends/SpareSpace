package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class home extends ListActivity {
    /*
    private String [] usernames;
    private static String [] titles;
    private String [] descriptions;
    private String [] locations;
    private String [] costs;
    private String [] obos;
    private String [] dimmensions;
    private String [] phones;
    private String [] emails;
    private String [] image;
    private String [] image2;
    */
    private static final String SERVER_ADDRESS = "http://sparespace.netai.net/";
    private static int drawableId;
    private static Bitmap bit;
    private static Drawable draw;
    private ImageView img_download;
    private int Jlength;
    private ArrayList<Bitmap> bitList;
    private int picCount;
    private ArrayList<String> usernames;
    private ArrayList<String> titles;
    private ArrayList<String> descriptions;
    private ArrayList<String> locations;
    private ArrayList<String> costs;
    private ArrayList<String> obos;
    private ArrayList<String> dimmensions;
    private ArrayList<String> phones;
    private ArrayList<String> emails;
    private ArrayList<String> image;
    private ArrayList<String> image2;

    static ListView list;
    static String[] web = {
            "Posting 1",
            "Posting 2",
            "Posting 3",
            "Posting 4",
            "Posting 5",
            "Posting 6",
            "Posting 7",
            "Posting 8",
            "Posting 9",
            "Posting 10",
            "Posting 12",
            "Posting 11"

    };
    static Integer[] imageId = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher

    };


    private String [] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayAdapter<String > adapter = new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_1,web);
        getListView().setAdapter(adapter);

        bitList = new ArrayList<Bitmap>();


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.d("YOUR TAG", "SUCESS");
                        /*
                        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                        builder.setMessage("Posting SYNC Success")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                        */
                        if (response.length() == 0) {
                            return;
                        }
                        Jlength = jsonResponse.length()/11;
                        int leng = jsonResponse.length()/11;
                        /*
                        usernames = new String[leng];
                        titles = new String[leng];
                        descriptions = new String[leng];
                        locations = new String[leng];
                        costs = new String[leng];
                        obos = new String [leng];
                        dimmensions = new String[leng];
                        phones = new String[leng];
                        emails = new String[leng];
                        image = new String[leng];
                        image2 = new String[leng];
                        */
                        usernames = new ArrayList<String>();
                        titles = new ArrayList<String>();
                        descriptions = new ArrayList<String>();
                        locations = new ArrayList<String>();
                        costs = new ArrayList<String>();
                        obos = new ArrayList<String>();
                        dimmensions = new ArrayList<String>();
                        phones = new ArrayList<String>();
                        emails = new ArrayList<String>();
                        image = new ArrayList<String>();
                        image2 = new ArrayList<String>();


                        Log.d("YOUR TAG", "INIT ARRAY DONE");
                        String length = "";
                        int index = 0;
                        for (int i = 0; i < leng; i ++) {
                            index = 11 * i;
                            /*
                            length = Integer.toString(index);
                            usernames = jsonResponse.getString(length);
                            length = Integer.toString(index + 1);
                            titles[i] = jsonResponse.getString(length);
                            length = Integer.toString(index+ 2);
                            descriptions[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 3);
                            locations[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 4);
                            costs[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 5);
                            obos[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 6);
                            dimmensions[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 7);
                            phones[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 8);
                            emails[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 9);
                            image[i] = jsonResponse.getString(length);
                            length = Integer.toString(index + 10);
                            image2[i] = jsonResponse.getString(length);
                            */
                            length = Integer.toString(index);
                            usernames.add(jsonResponse.getString(length));
                            length = Integer.toString(index + 1);
                            titles.add( jsonResponse.getString(length));
                            length = Integer.toString(index+ 2);
                            descriptions.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 3);
                            locations.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 4);
                            costs.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 5);
                            obos.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 6);
                            dimmensions.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 7);
                            phones.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 8);
                            emails.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 9);
                            image.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 10);
                            image2.add( jsonResponse.getString(length));


                        }


                        //String name = jsonResponse.getString("name");
                        //int age = jsonResponse.getInt("age");
                        //String user = jsonResponse.getString("username");
                    } else {
                        Log.d("YOUR TAG", "FAIL");
                        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                        builder.setMessage("Posting SYNC Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        PostRequest postRequest = new PostRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(home.this);
        queue.add(postRequest);
        Log.d("YOUR TAG", "BEFORE LISTVIEW ADAPTER");

        //String[] stockArr = new String[arrayList.size()];
        //stockArr = arrayList.toArray(stockArr);

        //creates adapter for listview



    }//end of oncreate

    public void sync_click(View v) {
        int picCount = 0;
        for (int i = 0; i < Jlength; i ++) {
            new DownloadImage(image.get(i)).execute();
            Log.d("YOUR TAG",image.get(i));
            Log.d("YOUR TAG", "Downloading");
            Log.d("YOUR TAG", Integer.toString(bitList.size()));
            picCount ++;
        }
        if (bitList.size() == Jlength) {
            CustomList adapter = new CustomList(home.this, titles, bitList);
            list = (ListView)findViewById(android.R.id.list);
            list.setAdapter(adapter);
            Log.d("YOUR TAG", "AFTER LIST VIEW ADAPTED");
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(home.this, "You Clicked at " + titles.get(+position), Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Log.d("YOUR TAG", Integer.toString(bitList.size()));
        }

        //CustomList adapter = new CustomList(home.this, titles, imageId);

    }


    private class DownloadImage extends AsyncTask<Void,Void,Bitmap> {
        String name;

        public DownloadImage(String name) {
            Log.d("YOUR TAG", "constructor");
            this.name = name;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String url = SERVER_ADDRESS + "pictures/" + name + ".jpg";
            Log.d("YOUR TAG", "DO IN BACKGROUND");
            try {
                Log.d("YOUR TAG", "TRYING");
                URLConnection connection = new URL(url).openConnection();
                Log.d("YOUR TAG", "CONNECCTION OPEN");
                connection.setConnectTimeout(1000*30);
                connection.setReadTimeout(1000*30);
                return BitmapFactory.decodeStream((InputStream)connection.getContent(),null,null);

            } catch( Exception e) {
                Log.d("YOUR TAG", "EXCEPTION");
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                Log.d("YOUR TAG", "BITMAP IS NOT NULL");
                //bit = bitmap;
                //draw = new BitmapDrawable(getResources(),bitmap);
                //img_download.setImageBitmap(bitmap);
                //drawableId = Integer.parseInt(img_download.getTag().toString());
                //download_im.setImageBitmap(bitmap);
                bitList.add(bitmap);
                Log.d("YOUR TAG", String.valueOf(bitList.get(0)));
                Log.d("YOUR TAG",Integer.toString(bitList.size()));
                if (bitList.size() == Jlength) {
                    CustomList adapter = new CustomList(home.this, titles, bitList);
                    //list = (ListView)findViewById(R.id.list);
                    list = (ListView) getListView();
                    list.setAdapter(adapter);
                    Log.d("YOUR TAG", "AFTER LIST VIEW ADAPTED");
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(home.this, "You Clicked at " +titles[+ position], Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Log.d("YOUR TAG", Integer.toString(bitList.size()));
                }

            } else
            {
                Log.d("YOUR TAG", "BITMAP IS NULL");
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
