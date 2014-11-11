package com.twochooseone.android.hearthstonehub.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christhoma on 11/10/14.
 */
public class CardsGridAdapter extends BaseAdapter  {

    ArrayList<Card> cards;
    Context context;
    private final LayoutInflater inflater;
    private static final String IMAGEURL = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/%s.png";

    public CardsGridAdapter(ArrayList<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
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
            holder.layout = (RelativeLayout) v.findViewById(R.id.card_grid_layout);
            holder.name = (TextView) v.findViewById(R.id.name);
            holder.description = (TextView) v.findViewById(R.id.description);
            holder.health = (TextView) v.findViewById(R.id.health);
            holder.mana = (TextView) v.findViewById(R.id.mana);
            holder.attack = (TextView) v.findViewById(R.id.attack);
            holder.image = (ImageView) v.findViewById(R.id.card_image);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.name.setText(card.name);
        if (!TextUtils.isEmpty(card.text)) {
            holder.description.setText(Html.fromHtml(card.text));
        } else {
            holder.description.setVisibility(View.GONE);
        }
        holder.attack.setText("" + card.attack);
        holder.mana.setText("" + card.cost);
        holder.health.setText("" + card.health);
        String url = String.format(IMAGEURL, card.id);
        Log.d(MainApp.TAG, "card name: " + card.name + "\n" + "url " + url);
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.higher_res_card)
                .fit()
                .into(holder.image);
        return v;
    }

    public class ViewHolder {
        RelativeLayout layout;
        TextView name;
        TextView description;
        TextView health;
        TextView mana;
        TextView attack;
        ImageView image;
    }
}
