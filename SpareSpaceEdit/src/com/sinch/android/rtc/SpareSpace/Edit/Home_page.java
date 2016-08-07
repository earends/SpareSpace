package com.sinch.android.rtc.SpareSpace.Edit;
/*
Description:
This class is the window class in between the login page and the sell, account, and home page
This activity is specifically meant to maintain the Tab host

 */

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
//import android.support.design.widget.TabLayout;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.ToggleButton;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.sinch.android.rtc.sample.messaging.R;

@SuppressWarnings("deprecation")
public class Home_page extends TabActivity {
    //private TabHost tabHost;

    public static String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        intializeTabHost();

        Intent intent = getIntent(); // grabs the username credentials from the server
        userName = intent.getStringExtra("username");


    }

    /**
     * Initializes and assigns the Tabs to a class
     */
    private void intializeTabHost() {

        // initializes tab host
        final TabHost host = getTabHost();
        Resources ressources = getResources();
        //TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        host.setup(this.getLocalActivityManager());




        //Tab 1 - home activity
        TabHost.TabSpec spec = host.newTabSpec("Tab One"); // gives an id to each tab, text is specific to tab in xml
        //spec.setContent(R.id.tab1);
        spec.setContent(new Intent(this, home.class)); // set activity to display for tab
        //spec.setIndicator("Tab One");
        spec.setIndicator("",ressources.getDrawable(R.mipmap.home)); // set name or icon over tab
        host.addTab(spec);  // add the tab details to the host

        //Tab 2 - sell activity
        spec = host.newTabSpec("Tab Two");
        //spec.setContent(R.id.tab2);
        spec.setContent(new Intent(this, sell.class));
        spec.setIndicator("",ressources.getDrawable(R.mipmap.sell));
        host.addTab(spec);

        //Tab 3 - account activity
        spec = host.newTabSpec("Tab Three");
        //spec.setContent(R.id.tab3);
        spec.setContent(new Intent(this, account.class));
        spec.setIndicator("",ressources.getDrawable(R.mipmap.account));
        host.addTab(spec);

        // creates a listener for each class within tab host
        // use below function (posibly) to activate on create function when each tab is pressed
        //startActivity(new Intent(presentActivity.this, NextActivity.class));
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                RelativeLayout tab1 = (RelativeLayout) findViewById(R.id.tab1);
                if( tabId.equals("Tab Three") ){
                    Log.d("YOUR TAG", "Account page clicked");
                    // account class
                    //Log.d("your tag",userName);
                    account.nameID.setText(Login.usernme);
                }
                if( tabId.equals("Tab Two") ){
                    Log.d("YOUR TAG", "Sell page clicked");
                    sell.posted = false;

                    //sell class
                    //sell.self.theMethodYouWantToCall();
                }
                if( tabId.equals("Tab One") ){
                    Log.d("HOME PAGE CLICKED", "SpareSpace");
                    //startActivity(new Intent(Home_page.this, Home_page.class));
                    //startActivity(new Intent(Home_page.this, Home_page.class));

                    //startActivity(new Intent(Home_page.this, home.class));
                    //home class
                    //home.self.theMethodYouWantToCall();
                }
            }
        });
    }



}
