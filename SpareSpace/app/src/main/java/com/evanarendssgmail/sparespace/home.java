package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class home extends Activity {
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

    static ListView list;
    static String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing"
            //"Itunes",
            //"Wordpress",
            //"Drupal",
            //"Google Plus",
            //"Twitter",
            //"Windows",
            //"Bing",
            //"Itunes",
            //"Wordpress",
            //"Drupal"
    };
    static Integer[] imageId = {
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
            //R.mipmap.ic_launcher,
            //R.mipmap.ic_launcher

    };


    private String [] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.d("YOUR TAG", "SUCESS");
                        AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                        builder.setMessage("Posting SYNC Success")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                        int leng = jsonResponse.length()/11;
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

                        Log.d("YOUR TAG", "INIT ARRAY DONE");
                        String length = "";
                        int index = 0;
                        for (int i = 0; i < leng; i ++) {
                            index = 11 * i;
                            length = Integer.toString(index);
                            usernames[i] = jsonResponse.getString(length);
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

                        }
                        Log.d("YOUR TAG", "FOR LOOP DONE");
                        Log.d("YOUR TAG", Integer.toString(jsonResponse.length()));
                        Log.d("YOUR TAG", jsonResponse.getString("0"));
                        Log.d("YOUR TAG", jsonResponse.getString("1"));
                        Log.d("YOUR TAG", jsonResponse.getString("2"));
                        Log.d("YOUR TAG", jsonResponse.getString("3"));
                        Log.d("YOUR TAG", jsonResponse.getString("4"));
                        Log.d("YOUR TAG", jsonResponse.getString("5"));
                        Log.d("YOUR TAG", jsonResponse.getString("6"));
                        Log.d("YOUR TAG", jsonResponse.getString("7"));
                        Log.d("YOUR TAG", jsonResponse.getString("8"));
                        Log.d("YOUR TAG", jsonResponse.getString("9"));
                        Log.d("YOUR TAG", jsonResponse.getString("10"));
                        Log.d("YOUR TAG", "LOG STATEMENTS DONE");

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

        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(titles));
        //String[] stockArr = new String[arrayList.size()];
        //stockArr = arrayList.toArray(stockArr);
        CustomList adapter = new CustomList(home.this, arrayList, imageId);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        Log.d("YOUR TAG", "AFTER LIST VIEW ADAPTED");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(home.this, "You Clicked at " +titles[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        //creates adapter for listview

    }//end of oncreate

    public void new_click(View v) {
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(titles));

        //CustomList adapter = new CustomList(home.this, titles, imageId);
        //list = (ListView)findViewById(R.id.list);
        CustomList adapter = new CustomList(home.this, arr, imageId);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(home.this, "You Clicked at " +titles[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }


}
