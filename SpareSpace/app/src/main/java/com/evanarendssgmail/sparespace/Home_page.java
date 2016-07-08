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
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //add another homepage
        TabHost.TabSpec tab = tabHost.newTabSpec("Home");
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Buy");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Sell");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Account");
        //TabHost.TabSpec tab4 = tabHost.newTabSpec("four");

        tab.setIndicator("Home");  // sets title
        tab.setContent(new Intent(this, home.class));


        tab1.setIndicator("Buy");
        tab1.setContent(new Intent(this, buy.class));


        tab2.setIndicator("Sell");
        tab2.setContent(new Intent(this, sell.class));

        tab3.setIndicator("Account");
        tab3.setContent(new Intent(this, account.class));

        //tab4.setIndicator("four");
        //tab4.setContent(new Intent(this, account.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab);

        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        //tabHost.addTab(tab4);



    }
        /*
        View tab1 = tabhost.findViewById(R.id.linearLayout);
        View tab2 = tabhost.findViewById(R.id.linearLayout2);
        View tab3 = tabhost.findViewById(R.id.linearLayout3);
        View tab4 = tabhost.findViewById(R.id.linearLayout4);

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setBackgroundColor(Color.rgb(255,0,0));
        tab2.setBackgroundColor(Color.rgb(0,255,0));
        tab3.setBackgroundColor(Color.rgb(0,150,0));
        tab4.setBackgroundColor(Color.rgb(0,255,255));
        */
        /*
        tabhost.setup();

        //Tab 1
        TabHost.TabSpec spec = tabhost.newTabSpec("Tab One");
        spec.setContent(R.id.linearLayout);
        spec.setIndicator("Tab One");
        tabhost.addTab(spec);

        //Tab 2
        spec = tabhost.newTabSpec("Tab Two");
        spec.setContent(R.id.linearLayout2);
        spec.setIndicator("Tab Two");
        tabhost.addTab(spec);

        //Tab 3
        spec = tabhost.newTabSpec("Tab Three");
        spec.setContent(R.id.linearLayout3);
        spec.setIndicator("Tab Three");
        tabhost.addTab(spec);

        //Tab 4
        spec = tabhost.newTabSpec("Tab Four");
        spec.setContent(R.id.linearLayout4);
        spec.setIndicator("Tab Four");
        tabhost.addTab(spec);
    }
    */


    /**
     * Intialize UI compoonents
     */
    private void initUI() {
        //TabHost host = getTabHost();
    }



}
