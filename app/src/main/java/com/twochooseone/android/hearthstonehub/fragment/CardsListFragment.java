package com.twochooseone.android.hearthstonehub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.adapter.CardsGridAdapter;
import com.twochooseone.android.hearthstonehub.api.loader.CardLoader;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/8/14.
 */
public class CardsListFragment extends HearthstoneBaseFragment implements LoaderManager.LoaderCallbacks<Map<String, Object>> {

    @InjectView(R.id.progress)
    ProgressBar progressBar;
    @InjectView(R.id.test_tv)
    TextView tview;
    @InjectView(R.id.cards_grid)
    GridView cardsGrid;
    @Inject
    Manager manager;
    @Inject
    Database db;
    CardsGridAdapter adapter;
    Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cards_list, container, false);
        ButterKnife.inject(this, v);
//            Map<String, Object> map = db.getAllDocs(new QueryOptions());
//            String id = ((QueryRow)((ArrayList)map.get("rows")).get(0)).getDocument().getId();
//            Document doc = db.getDocument(id);
//            Map<String, Object> props = doc.getProperties();
////            Document doc = db.getDocument((String)((ArrayList)map.get("rows")).get(0));
////            tview.setText((String)doc.getProperty("name"));
//            tview.setText((String)props.get("name"));
//            com.couchbase.lite.Query query = db.createAllDocumentsQuery();
//            query.setStartKey("name");
//            QueryEnumerator rowEnum = query.run();
//            for (Iterator<QueryRow> it = rowEnum; it.hasNext();) {
//                QueryRow row = it.next();
//            }
            /*
                Query query1 = db.createAllDocumentsQuery();
                List<Object> keys = new ArrayList<Object>();
                keys.add("Life Tap");
                query1.setKeys(keys);
                QueryEnumerator rowEnum = query1.run();
             */
        getActivity().getSupportLoaderManager().restartLoader(1, null, this);
        return v;
    }

    @Override
    public Loader<Map<String, Object>> onCreateLoader(int i, Bundle bundle) {
        return new CardLoader(getActivity(), db);
    }

    @Override
    public void onLoadFinished(Loader<Map<String, Object>> hashMapLoader, Map<String, Object> stringObjectHashMap) {
        progressBar.setVisibility(View.GONE);
        String json = gson.toJson(stringObjectHashMap);
        Type type = new TypeToken<Card>() {
        }.getType();
        Card card = gson.fromJson(json , type);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(card);
        adapter = new CardsGridAdapter(cards, getActivity());
        cardsGrid.setAdapter(adapter);
        cardsGrid.setVisibility(View.VISIBLE);
//        tview.setText((String)stringObjectHashMap.get("name") + " " +  (String)stringObjectHashMap.get("text"));
//        tview.setText(card.name);
    }

    @Override
    public void onLoaderReset(Loader<Map<String, Object>> hashMapLoader) {

    }
}
