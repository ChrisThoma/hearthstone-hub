package com.twochooseone.android.hearthstonehub.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/12/14.
 */
public class CardDetailActivity extends HearthstoneBaseActivity {

    public static final String CARDJSON = "CardJson";
    private static final String IMAGEURL = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/%s.png";
    public Card card;
    Gson gson = new Gson();
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.health)
    TextView health;
    @InjectView(R.id.mana)
    TextView mana;
    @InjectView(R.id.attack)
    TextView attack;
    @InjectView(R.id.description)
    TextView description;
    @InjectView(R.id.type)
    TextView type;
    @InjectView(R.id.player_class)
    TextView playerClass;
    @InjectView(R.id.race)
    TextView race;
    @InjectView(R.id.flavor)
    TextView flavor;
    @InjectView(R.id.rarity)
    TextView rarity;
    @InjectView(R.id.play_text)
    TextView playText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        card = gson.fromJson(getIntent().getExtras().getString(CARDJSON), Card.class);
        getSupportActionBar().setTitle(card.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String url = String.format(IMAGEURL, card.id);
        Picasso.with(this)
                .load(url)
                .fit()
                .into(image);
        name.setText(card.name);

        health.setText("" + card.health);
        mana.setText("" + card.cost);
        attack.setText("" + card.attack);

        if (!TextUtils.isEmpty(card.text)) {
            description.setText("Description: " + Html.fromHtml(card.text));
            description.setVisibility(View.VISIBLE);
        } else {
            description.setVisibility(View.GONE);
        }

        type.setText("Type: " + card.type);

        if (!TextUtils.isEmpty(card.playerClass)) {
            playerClass.setText("Class: " + card.playerClass);
            playerClass.setVisibility(View.VISIBLE);
        } else {
            playerClass.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(card.race)) {
            race.setText("Race: " + card.race);
            race.setVisibility(View.VISIBLE);
        } else {
            race.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(card.flavor)) {
            flavor.setText("Flavor: " + Html.fromHtml(card.flavor));
            flavor.setVisibility(View.VISIBLE);
        } else {
            flavor.setVisibility(View.GONE);
        }

        rarity.setText("Rarity: " + card.rarity);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
