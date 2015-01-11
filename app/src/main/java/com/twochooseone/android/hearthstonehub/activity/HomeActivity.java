package com.twochooseone.android.hearthstonehub.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.twochooseone.android.hearthstonehub.Const;
import com.twochooseone.android.hearthstonehub.R;
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
    @InjectView(R.id.logo)
    ImageView logo;
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_drawable);

        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        int newWidth = getWindowManager().getDefaultDisplay().getWidth(); //this method should return the width of device screen.
        newWidth = (int)(newWidth * .8);
        float scaleFactor = (float)newWidth/(float)imageWidth;
        int newHeight = (int)(imageHeight * scaleFactor);

        bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        logo.setImageBitmap(bitmap);
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
            InputStream is = getAssets().open(Const.CARDS_JSONPATH);
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
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(CardsListFragment.class.getName());
                if (fragment == null) {
                    fragment = CardsListFragment.instantiate(this, CardsListFragment.class.getName());
                }
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment, CardsListFragment.class.getName()).commit();
                break;
            case R.id.drawer_item_naxx:
                changeSelected(drawerNaxx);
                fragment = getSupportFragmentManager().findFragmentByTag(NaxxramasFragment.class.getName());
                if (fragment == null) {
                    fragment = CardsListFragment.instantiate(this, NaxxramasFragment.class.getName());
                }
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment, NaxxramasFragment.class.getName()).commit();
                break;
            case R.id.drawer_item_arena:
                changeSelected(drawerArena);
                fragment = getSupportFragmentManager().findFragmentByTag(ArenaTiersFragment.class.getName());
                if (fragment == null) {
                    fragment = CardsListFragment.instantiate(this, ArenaTiersFragment.class.getName());
                }
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment, ArenaTiersFragment.class.getName()).commit();
                break;
            case R.id.drawer_item_your_decks:
                changeSelected(drawerYourDecks);
                fragment = getSupportFragmentManager().findFragmentByTag(YourDecksFragment.class.getName());
                if (fragment == null) {
                    fragment = CardsListFragment.instantiate(this, YourDecksFragment.class.getName());
                }
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame_container, fragment, YourDecksFragment.class.getName()).commit();
                break;
        }
        drawerLayout.closeDrawers();
    }

    public void changeSelected(TextView nextSelected) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            selected.setBackgroundResource(R.drawable.drawer_pressed_ripple);
        } else {
            selected.setBackgroundResource(R.drawable.drawer_pressed);
        }
        selected.setTextColor(Color.WHITE);
        selected = nextSelected;
        selected.setBackgroundColor(getResources().getColor(R.color.pressed_grey));
        selected.setTextColor(getResources().getColor(R.color.accent));
    }
}
