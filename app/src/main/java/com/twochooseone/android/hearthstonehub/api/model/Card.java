package com.twochooseone.android.hearthstonehub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by christhoma on 11/8/14.
 */
public class Card implements Comparator<Card>, Comparable<Card> {

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

    @Override
    public int compare(Card lhs, Card rhs) {
        Card lcard = lhs;
        Card rcard = rhs;
        if (lcard == null && rcard == null) {
            return 0;
        } else if (lcard == null && rcard != null) {
            return -1;
        } else if (lcard != null && rcard == null) {
            return 1;
        }
        return lcard.name.compareTo(rcard.name);
    }

    @Override
    public int compareTo(Card another) {
        return this.name.compareTo(another.name);
    }
}
