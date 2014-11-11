package com.twochooseone.android.hearthstonehub.api.loader;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryOptions;
import com.couchbase.lite.QueryRow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oak.util.OakAsyncLoader;

/**
 * Created by christhoma on 11/10/14.
 */
public class CardLoader extends OakAsyncLoader<ArrayList<Card>> {

    Database db;
    Gson gson = new Gson();

    public CardLoader(Context context, Database db) {
        super(context);
        this.db = db;
    }

    @Override
    public ArrayList<Card> loadInBackground() {
        try {
            Query query1 = db.createAllDocumentsQuery();
            QueryEnumerator rowEnum = query1.run();
            ArrayList<Card> cards = new ArrayList<Card>();
            for (Iterator<QueryRow> it = rowEnum; it.hasNext();) {
                QueryRow row = it.next();
                String json = gson.toJson(row.getDocument().getProperties());
                Type type = new TypeToken<Card>() {
                }.getType();
                Card card = gson.fromJson(json , type);
                if (!card.id.contains("e") && !card.id.contains("o") && !card.type.toLowerCase().contains("hero")) {
                    cards.add(card);
                }
            }
            Collections.sort(cards);
            return cards;
        } catch (CouchbaseLiteException e) {
            Log.d(MainApp.TAG, e.getMessage());
        }
        return null;
    }
}
