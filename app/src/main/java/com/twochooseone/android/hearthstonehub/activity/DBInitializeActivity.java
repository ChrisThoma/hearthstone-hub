package com.twochooseone.android.hearthstonehub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.api.loader.DatabaseLoader;
import com.twochooseone.android.hearthstonehub.api.model.Card;
import com.twochooseone.android.hearthstonehub.api.model.CardsList;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

import butterknife.InjectView;

/**
 * Created by christhoma on 11/9/14.
 */
public class DBInitializeActivity extends HearthstoneBaseActivity {

    @InjectView(R.id.progress)
    ProgressBar progressBar;

    LoaderManager.LoaderCallbacks<Boolean> dbLoaderCallback = new LoaderManager.LoaderCallbacks<Boolean>() {
        @Override
        public Loader<Boolean> onCreateLoader(int i, Bundle bundle) {
            return new DatabaseLoader(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<Boolean> booleanLoader, Boolean aBoolean) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onLoaderReset(Loader<Boolean> booleanLoader) {
            //
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_initialize);
        getSupportLoaderManager().restartLoader(0, null, dbLoaderCallback);
    }
}
