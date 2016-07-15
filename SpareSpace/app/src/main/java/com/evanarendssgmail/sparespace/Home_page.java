package com.evanarendssgmail.sparespace;

import android.app.ActivityGroup;
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
public class Home_page extends ActivityGroup {
    //private TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        intializeTabHost();
    }


    private void intializeTabHost() {

        Resources ressources = getResources();
        TabHost host = (TabHost)findViewById(R.id.tabHost);
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

    }



}
