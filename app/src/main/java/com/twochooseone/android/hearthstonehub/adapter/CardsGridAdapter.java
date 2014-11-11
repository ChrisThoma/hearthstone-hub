package com.twochooseone.android.hearthstonehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christhoma on 11/10/14.
 */
public class CardsGridAdapter extends BaseAdapter  {

    ArrayList<Card> cards;
    private final LayoutInflater inflater;

    public CardsGridAdapter(ArrayList<Card> cards, Context context) {
        this.cards = cards;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        Card card = cards.get(position);
        if (v==null) {
            v = inflater.inflate(R.layout.card_grid_view, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.name);
            holder.description = (TextView) v.findViewById(R.id.description);
            holder.health = (TextView) v.findViewById(R.id.health);
            holder.mana = (TextView) v.findViewById(R.id.mana);
            holder.attack = (TextView) v.findViewById(R.id.attack);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.name.setText(card.name);
        holder.description.setText(card.text);
        holder.attack.setText("" + card.attack);
        holder.mana.setText("" + card.cost);
        holder.health.setText("" + card.health);
        return v;
    }

    public class ViewHolder {
        TextView name;
        TextView description;
        TextView health;
        TextView mana;
        TextView attack;
    }
}
