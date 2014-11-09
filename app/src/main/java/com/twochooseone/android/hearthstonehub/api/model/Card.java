package com.twochooseone.android.hearthstonehub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by christhoma on 11/8/14.
 */
public class Card {

    public String name;
    public String type;
    public int cost;
    public int attack;
    public int health;
    @SerializedName("card_type")
    public String cardType;
    public String rarity;
    public String text;
    public String flavor;
    public ArrayList<String> mechanics;
    public String id;

}
