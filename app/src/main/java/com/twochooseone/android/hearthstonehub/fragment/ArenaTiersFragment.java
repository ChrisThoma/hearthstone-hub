package com.twochooseone.android.hearthstonehub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twochooseone.android.hearthstonehub.Const;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.activity.ArenaTiersDetailActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/8/14.
 */
public class ArenaTiersFragment extends HearthstoneBaseFragment {

    @InjectView(R.id.druid_arena_card)
    CardView druidCard;
    @InjectView(R.id.hunter_arena_card)
    CardView hunterCard;
    @InjectView(R.id.mage_arena_card)
    CardView mageCard;
    @InjectView(R.id.paladin_arena_card)
    CardView paladinCard;
    @InjectView(R.id.priest_arena_card)
    CardView priestCard;
    @InjectView(R.id.rogue_arena_card)
    CardView rogueCard;
    @InjectView(R.id.shaman_arena_card)
    CardView shamanCard;
    @InjectView(R.id.warlock_arena_card)
    CardView warlockCard;
    @InjectView(R.id.warrior_arena_card)
    CardView warriorCard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_arena_tiers, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        druidCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.DRUID);
                startActivity(intent);
            }
        });
        hunterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.HUNTER);
                startActivity(intent);
            }
        });
        mageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.MAGE);
                startActivity(intent);
            }
        });
        paladinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.PALADIN);
                startActivity(intent);
            }
        });
        priestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.PRIEST);
                startActivity(intent);
            }
        });
        rogueCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.ROGUE);
                startActivity(intent);
            }
        });
        shamanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.SHAMAN);
                startActivity(intent);
            }
        });
        warlockCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.WARLOCK);
                startActivity(intent);
            }
        });
        warriorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArenaTiersDetailActivity.class);
                intent.putExtra(Const.ARENA_CLASS, Const.WARRIOR);
                startActivity(intent);
            }
        });
    }


}
