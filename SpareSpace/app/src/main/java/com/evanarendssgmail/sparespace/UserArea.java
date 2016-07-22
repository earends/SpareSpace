package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserArea extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        TextView view = (TextView) findViewById(R.id.textView2);
        //Posting post = new Posting("title","description","location","cost","dimmension","obo","phone","email");
        //Posting post2 = new Posting("title2","description2","location2","cost2","dimmension2","obo2","phone2","email2");
    }
}