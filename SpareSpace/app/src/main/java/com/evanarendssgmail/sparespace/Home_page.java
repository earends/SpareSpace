package com.evanarendssgmail.sparespace;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.ToggleButton;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Home_page extends TabActivity {
    //private TabHost host;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initUI();
        intializeTabHost();
    }


    private void intializeTabHost() {

        Resources ressources = getResources();
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //add another homepage
        TabHost.TabSpec tab = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Buy");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Sell");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Account");


        tab.setIndicator("",ressources.getDrawable(R.mipmap.ic_launcher));  // sets title
        tab.setContent(new Intent(this, home.class));


        tab1.setIndicator("Buy");
        tab1.setContent(new Intent(this, buy.class));


        tab2.setIndicator("Sell");
        tab2.setContent(new Intent(this, sell.class));

        tab3.setIndicator("Account");
        tab3.setContent(new Intent(this, account.class));

        //tab4.setIndicator("four");
        //tab4.setContent(new Intent(this, account.class));
        tabHost.addTab(tab);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        //tabHost.addTab(tab4);



    }



    /**
     * Intialize UI compoonents
     */
    private void initUI() {
        //TabHost host = getTabHost();
    }



}
