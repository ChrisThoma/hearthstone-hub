package com.twochooseone.android.hearthstonehub.api.loader;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
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

            /*
                Querying a Couchbase Lite database involves first creating a view that indexes the keys you’re interested in
                and then running a query to get the results of the view for the key or range of keys you’re interested in.
                The view is persistent, like a SQL index.

                These key-value pairs get indexed, ordered by key, and can then be queried efficiently, again by key
             */
            com.couchbase.lite.View view = db.getView("loadAllCards");
            view.setMap(new Mapper() {
                /*
                    A map that has the contents of the document being indexed.
                    A function called emitter that takes the parameters key and value. This is the function your code calls to emit a key-value pair into the view’s index.
                 */
                @Override
                public void map(Map<String, Object> stringObjectMap, Emitter emitter) {
                    emitter.emit(stringObjectMap.get("name").toString(), stringObjectMap);
                }
            }, "1.0");

//            db.getView("loadAllCards").createQuery().run();
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
                if (card != null & !TextUtils.isEmpty(card.text)) {
                    card.htmlText = Html.fromHtml(card.text);
                }
            }
            Log.d(MainApp.TAG, "START");
            Collections.sort(cards);
            Log.d(MainApp.TAG, "End");
            return cards;
        } catch (CouchbaseLiteException e) {
            Log.d(MainApp.TAG, e.getMessage());
        }
        return null;
    }
}
