package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class account extends Activity {


    public static account self;
    public static TextView nameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        nameID = (TextView) findViewById(R.id.name_id);
    }
    
    public void setUp() {
        Log.d(Home_page.userName,"TAG");
        nameID = (TextView) findViewById(R.id.name_id);

        nameID.setText(Home_page.userName);
    }

    public void settings_click(View v) {
        Intent i = new Intent(getApplicationContext(),settings.class);
        startActivity(i);
    }
}
