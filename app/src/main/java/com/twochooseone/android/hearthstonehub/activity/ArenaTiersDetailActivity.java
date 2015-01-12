package com.twochooseone.android.hearthstonehub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.twochooseone.android.hearthstonehub.Const;
import com.twochooseone.android.hearthstonehub.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 1/11/15.
 */
public class ArenaTiersDetailActivity extends HearthstoneBaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.arena_detail_class_text)
    TextView classText;
    @InjectView(R.id.arena_detail_class_image)
    ImageView classImage;
    @InjectView(R.id.arena_detail_spinner)
    Spinner raritySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena_tiers_detail);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.arena_rankings_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String arenaClass = intent.getStringExtra(Const.ARENA_CLASS);

        classText.setText(arenaClass.toUpperCase());
        if (arenaClass.equals(Const.DRUID)) {
            classImage.setImageResource(R.drawable.icon_class_druid);
        } else if(arenaClass.equals(Const.HUNTER)) {
            classImage.setImageResource(R.drawable.icon_class_hunter);
        } else if(arenaClass.equals(Const.MAGE)) {
            classImage.setImageResource(R.drawable.icon_class_mage);
        } else if(arenaClass.equals(Const.PALADIN)) {
            classImage.setImageResource(R.drawable.icon_class_paladin);
        } else if(arenaClass.equals(Const.PRIEST)) {
            classImage.setImageResource(R.drawable.icon_class_priest);
        } else if(arenaClass.equals(Const.ROGUE)) {
            classImage.setImageResource(R.drawable.icon_class_rogue);
        } else if(arenaClass.equals(Const.SHAMAN)) {
            classImage.setImageResource(R.drawable.icon_class_shaman);
        } else if(arenaClass.equals(Const.WARLOCK)) {
            classImage.setImageResource(R.drawable.icon_class_warlock);
        } else if(arenaClass.equals(Const.WARRIOR)) {
            classImage.setImageResource(R.drawable.icon_class_warrior);
        }

        RarityAdapter rarityAdapter = new RarityAdapter(this, getResources().getStringArray(R.array.arena_rarities));
        rarityAdapter.setDropDownViewResource(R.layout.rarity_spinner_dropdown_item);
        raritySpinner.setAdapter(rarityAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class RarityAdapter extends ArrayAdapter<String> {

        String[] items;
        Activity activity;

        public RarityAdapter(Activity activity, String[] items) {
            super(activity, R.layout.rarity_spinner_item, items);
            this.items = items;
            this.activity = activity;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                LayoutInflater inflater = activity.getLayoutInflater();
                v = inflater.inflate(R.layout.rarity_spinner_item, null);
            }
            TextView spinnerLabel = (TextView) v;
            spinnerLabel.setText(items[position]);
            if (position == 0) {
                spinnerLabel.setTextColor(Color.WHITE);
            } else if (position == 1) {
                spinnerLabel.setTextColor(getResources().getColor(R.color.rare));
            } else if (position == 2) {
                spinnerLabel.setTextColor(getResources().getColor(R.color.epic));
            } else if (position == 3) {
                spinnerLabel.setTextColor(getResources().getColor(R.color.legendary));
            }
            return spinnerLabel;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = super.getDropDownView(position, convertView, parent);

            if (v == null) {
                v = new TextView(activity);
            }

            if (position == 0) {
                ((TextView)v).setTextColor(Color.BLACK);
            } else if (position == 1) {
                ((TextView)v).setTextColor(getResources().getColor(R.color.rare));
            } else if (position == 2) {
                ((TextView)v).setTextColor(getResources().getColor(R.color.epic));
            } else if (position == 3) {
                ((TextView)v).setTextColor(getResources().getColor(R.color.legendary));
            }
            return v;
        }
    }

}
