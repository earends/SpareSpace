package com.sinch.android.rtc.sample.messaging;
/*
Description: This activity will give the app user an description of the app, and the company details
 */

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

    }
}
