package com.twochooseone.android.hearthstonehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.activity.CardDetailActivity;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christhoma on 11/10/14.
 */
public class CardsGridAdapter extends RecyclerView.Adapter<CardsGridAdapter.ViewHolder> {

    ArrayList<Card> cards;
    Context context;
    Gson gson = new Gson();
    private final LayoutInflater inflater;
    private static final String IMAGEURL = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/%s.png";

    public CardsGridAdapter(ArrayList<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
        if (context != null) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } else {
            context = MainApp.getContext();
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (cards != null && cards.size() > 0) {
            Card card = cards.get(position);
            holder.name.setText(card.name);
            if (!TextUtils.isEmpty(card.text)) {
                holder.description.setVisibility(View.VISIBLE);
                holder.description.setText(card.htmlText);
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
                    .fit()
                    .into(holder.image);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CardDetailActivity.class);
                    intent.putExtra(CardDetailActivity.CARDJSON, gson.toJson(cards.get(position)));
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        TextView description;
        TextView health;
        TextView mana;
        TextView attack;
        ImageView image;

        public ViewHolder(View layout) {
            super(layout);
            cardView = (CardView) layout.findViewById(R.id.grid_cardview);
            name = (TextView) layout.findViewById(R.id.name);
            description = (TextView) layout.findViewById(R.id.description);
            health = (TextView) layout.findViewById(R.id.health);
            mana = (TextView) layout.findViewById(R.id.mana);
            attack = (TextView) layout.findViewById(R.id.attack);
            image = (ImageView) layout.findViewById(R.id.card_image);
        }
    }


    /* OLD IMPLEMENTATION OF ADAPTER */
//    @Override
//    public int getCount() {
//        return cards.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return cards.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//        final ViewHolder holder;
//        Card card = cards.get(position);
//        if (v==null) {
//            v = inflater.inflate(R.layout.card_grid_view, parent, false);
//            holder = new ViewHolder();
//            holder.layout = (RelativeLayout) v.findViewById(R.id.card_grid_layout);
//            holder.name = (TextView) v.findViewById(R.id.name);
//            holder.description = (TextView) v.findViewById(R.id.description);
//            holder.health = (TextView) v.findViewById(R.id.health);
//            holder.mana = (TextView) v.findViewById(R.id.mana);
//            holder.attack = (TextView) v.findViewById(R.id.attack);
//            holder.image = (ImageView) v.findViewById(R.id.card_image);
//            v.setTag(holder);
//        } else {
//            holder = (ViewHolder) v.getTag();
//        }
//        holder.name.setText(card.name);
//        if (!TextUtils.isEmpty(card.text)) {
//            holder.description.setVisibility(View.VISIBLE);
//            holder.description.setText(card.htmlText);
//        } else {
//            holder.description.setVisibility(View.GONE);
//        }
//        holder.attack.setText("" + card.attack);
//        holder.mana.setText("" + card.cost);
//        holder.health.setText("" + card.health);
//        String url = String.format(IMAGEURL, card.id);
//        Log.d(MainApp.TAG, "card name: " + card.name + "\n" + "url " + url);
//        Picasso.with(context)
//                .load(url)
//                .fit()
//                .into(holder.image);
//        return v;
//    }
}
