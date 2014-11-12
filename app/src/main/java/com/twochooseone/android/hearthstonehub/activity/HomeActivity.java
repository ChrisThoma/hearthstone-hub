package com.twochooseone.android.hearthstonehub.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.api.model.Card;
import com.twochooseone.android.hearthstonehub.api.model.CardsList;
import com.twochooseone.android.hearthstonehub.fragment.ArenaTiersFragment;
import com.twochooseone.android.hearthstonehub.fragment.CardsListFragment;
import com.twochooseone.android.hearthstonehub.fragment.NaxxramasFragment;
import com.twochooseone.android.hearthstonehub.fragment.YourDecksFragment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/7/14.
 */
public class HomeActivity extends HearthstoneBaseActivity implements View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.drawer_item_cards_list)
    TextView drawerCardsList;
    @InjectView(R.id.drawer_item_naxx)
    TextView drawerNaxx;
    @InjectView(R.id.drawer_item_arena)
    TextView drawerArena;
    @InjectView(R.id.drawer_item_your_decks)
    TextView drawerYourDecks;
    TextView selected;
    @InjectView(R.id.frame_container)
    FrameLayout frameLayout;
    Gson gson = new Gson();
    CardsList cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HEARTHSTONE HUB");
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.okay, R.string.oak_test) {

        };
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerCardsList.setOnClickListener(this);
        drawerNaxx.setOnClickListener(this);
        drawerArena.setOnClickListener(this);
        drawerYourDecks.setOnClickListener(this);

        selected = drawerCardsList;
        drawerCardsList.callOnClick();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("json/cards.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer_item_cards_list:
                changeSelected(drawerCardsList);
                Fragment fragment = new CardsListFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
            case R.id.drawer_item_naxx:
                changeSelected(drawerNaxx);
                fragment = new NaxxramasFragment();
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
            case R.id.drawer_item_arena:
                changeSelected(drawerArena);
                fragment = new ArenaTiersFragment();
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
            case R.id.drawer_item_your_decks:
                changeSelected(drawerYourDecks);
                fragment = new YourDecksFragment();
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment).commit();
                break;
        }
        drawerLayout.closeDrawers();
    }

    public void changeSelected(TextView nextSelected) {
        selected.setBackgroundResource(R.drawable.drawer_pressed);
        selected = nextSelected;
        selected.setBackgroundColor(getResources().getColor(R.color.pressed_grey));
    }
}
