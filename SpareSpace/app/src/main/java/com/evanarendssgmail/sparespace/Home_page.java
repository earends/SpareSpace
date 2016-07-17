package com.evanarendssgmail.sparespace;

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

@SuppressWarnings("deprecation")
public class Home_page extends TabActivity {
    //private TabHost tabHost;

    public static String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        intializeTabHost();

        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

    }


    private void intializeTabHost() {
        final TabHost host = getTabHost();

        Resources ressources = getResources();
        //TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        host.setup(this.getLocalActivityManager());


        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        //spec.setContent(R.id.tab1);
        spec.setContent(new Intent(this, home.class));
        //spec.setIndicator("Tab One");
        spec.setIndicator("",ressources.getDrawable(R.mipmap.ic_launcher));
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        //spec.setContent(R.id.tab2);
        spec.setContent(new Intent(this, ImageUpload.class));
        spec.setIndicator("Sell");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        //spec.setContent(R.id.tab3);
        spec.setContent(new Intent(this, account.class));

        spec.setIndicator("Account");
        host.addTab(spec);


        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                RelativeLayout tab1 = (RelativeLayout) findViewById(R.id.tab1);
                if( tabId.equals("Tab Three") ){
                    Log.d("your tag",userName);
                    account.nameID.setText(userName);
                }
                if( tabId.equals("tab2") ){
                    //home.self.theMethodYouWantToCall();
                }
                if( tabId.equals("tab3") ){
                    //ImageUpload.self.theMethodYouWantToCall();
                }
            }
        });
    }



}
