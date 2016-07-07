package com.evanarendssgmail.sparespace;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.ToggleButton;

public class Home_page extends AppCompatActivity {
    //private TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initUI();
        intializeTabHost();
    }


    private void intializeTabHost() {
        Resources ressources = getResources();
        //TabHost tabHost = getTabHost();
        //tabHost = (TabHost) findViewById(R.id.tabHost);
        // Android tab
        Intent intentAndroid = new Intent().setClass(this, Info.class);
        TabHost.TabSpec tabSpecAndroid = tabHost
                .newTabSpec("Android")
                .setIndicator("", ressources.getDrawable(R.drawable.front_logo))
                .setContent(intentAndroid);

        // Apple tab
        Intent intentApple = new Intent().setClass(this, Info.class);
        TabHost.TabSpec tabSpecApple = tabHost
                .newTabSpec("Apple")
                .setIndicator("", ressources.getDrawable(R.color.green))
                .setContent(intentApple);

        // Windows tab
        Intent intentWindows = new Intent().setClass(this, Info.class);
        TabHost.TabSpec tabSpecWindows = tabHost
                .newTabSpec("Windows")
                .setIndicator("", ressources.getDrawable(R.color.red))
                .setContent(intentWindows);

        // Blackberry tab
        Intent intentBerry = new Intent().setClass(this, Info.class);
        TabHost.TabSpec tabSpecBerry = tabHost
                .newTabSpec("Berry")
                .setIndicator("", ressources.getDrawable(R.color.blue))
                .setContent(intentBerry);

        // add all tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecApple);
        tabHost.addTab(tabSpecWindows);
        tabHost.addTab(tabSpecBerry);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(2);
    }


    /**
     * Intialize UI compoonents
     */
    private void initUI() {
        //tabHost = (TabHost) findViewById(R.id.tabHost);
    }
}
