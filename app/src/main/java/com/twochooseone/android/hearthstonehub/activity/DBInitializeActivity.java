package com.twochooseone.android.hearthstonehub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.api.model.Card;
import com.twochooseone.android.hearthstonehub.api.model.CardsList;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by christhoma on 11/9/14.
 */
public class DBInitializeActivity extends HearthstoneBaseActivity {

    Gson gson = new Gson();
    CardsList cardsList;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDB();
    }

    public void initializeDB() {
        Manager manager = null;
        try {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            Log.e(MainApp.TAG, e.getMessage());
        }

        String dbName = "hearthstone_db";
        if (!Manager.isValidDatabaseName(dbName)) {
            Log.e(MainApp.TAG, "Bad db name");
            return;
        }

        try {
            if (manager != null) {
                db = manager.getDatabase(dbName);
                if (db != null && db.getDocumentCount() <= 0) {
                    Type type = new TypeToken<CardsList>() {
                    }.getType();
                    cardsList = gson.fromJson(loadJSONFromAsset(), type);
                    addCardsToDB(cardsList);
                }
            } else {
                return;
            }
        } catch (CouchbaseLiteException e) {
            Log.e(MainApp.TAG, "Cannot get db");
            return;
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("json/cards.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void addCardsToDB(CardsList cardsList) {
        if (cardsList != null && cardsList.cards != null && cardsList.cards.size() > 0) {
            for (Card card : cardsList.cards) {
                Document doc  = db.createDocument();
                HashMap<String, Object> jsonObj = new HashMap<String, Object>();
                jsonObj.put("name", card.name);
                jsonObj.put("type", card.type);
                jsonObj.put("cost", card.cost);
                jsonObj.put("attack", card.attack);
                jsonObj.put("health", card.health);
                jsonObj.put("cardType", card.cardType);
                jsonObj.put("rarity", card.rarity);
                jsonObj.put("text", card.text);
                jsonObj.put("flavor", card.flavor);
                jsonObj.put("mechanics", card.mechanics);
                jsonObj.put("id", card.id);
                try {
                    doc.putProperties(jsonObj);
                } catch (CouchbaseLiteException e) {
                    Log.d(MainApp.TAG, e.getMessage());
                }
            }
        }
    }
}
