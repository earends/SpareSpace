package com.sinch.android.rtc.sample.messaging;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void logout_click(View v) {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
