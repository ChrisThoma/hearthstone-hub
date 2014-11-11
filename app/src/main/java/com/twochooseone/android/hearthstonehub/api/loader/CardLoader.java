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
import com.twochooseone.android.hearthstonehub.MainApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oak.util.OakAsyncLoader;

/**
 * Created by christhoma on 11/10/14.
 */
public class CardLoader extends OakAsyncLoader<Map<String, Object>> {

    Database db;

    public CardLoader(Context context, Database db) {
        super(context);
        this.db = db;
    }

    @Override
    public Map<String, Object> loadInBackground() {
        try {
            Query query1 = db.createAllDocumentsQuery();
            QueryEnumerator rowEnum = query1.run();
            Document doc = rowEnum.getRow(0).getDocument();
            return doc.getProperties();
        } catch (CouchbaseLiteException e) {
            Log.d(MainApp.TAG, e.getMessage());
        }
        return null;
    }
}
