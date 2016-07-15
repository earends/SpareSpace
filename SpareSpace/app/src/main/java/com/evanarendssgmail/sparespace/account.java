package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class account extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    public void settings_click(View v) {
        Intent i = new Intent(getApplicationContext(),settings.class);
        startActivity(i);
    }
}
