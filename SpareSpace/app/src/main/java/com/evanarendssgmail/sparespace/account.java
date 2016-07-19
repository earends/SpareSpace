package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//in use currently
public class account extends Activity {


    public static account self;
    public static TextView nameID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        nameID = (TextView) findViewById(R.id.name_id);
    }


    /**
     * Sets the Textview to the right of the icon to the name of the logged in user
     */
    public void setUp() {
        Log.d(Home_page.userName,"TAG");
        nameID = (TextView) findViewById(R.id.name_id);
        nameID.setText(Home_page.userName);
    }


    /**
     * if pressed goes to the settings activity
     * @param v
     */
    public void settings_click(View v) {
        Intent i = new Intent(getApplicationContext(),settings.class);
        startActivity(i);
    }
}
