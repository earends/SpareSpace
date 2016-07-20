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

public class home extends Activity {

    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal",
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

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

        //creates adapter for listview
        CustomList adapter = new CustomList(home.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(home.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }
}
