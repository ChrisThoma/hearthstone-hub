package com.twochooseone.android.hearthstonehub.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;


import com.twochooseone.android.hearthstonehub.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/7/14.
 */
public class HomeActivity extends HearthstoneBaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HEARTHSTONE HUB");

    }


}
