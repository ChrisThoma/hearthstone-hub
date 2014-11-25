package com.twochooseone.android.hearthstonehub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Mapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.twochooseone.android.hearthstonehub.R;
import com.twochooseone.android.hearthstonehub.activity.CardDetailActivity;
import com.twochooseone.android.hearthstonehub.adapter.CardsGridAdapter;
import com.twochooseone.android.hearthstonehub.api.loader.CardLoader;
import com.twochooseone.android.hearthstonehub.api.model.Card;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by christhoma on 11/8/14.
 */
public class CardsListFragment extends HearthstoneBaseFragment implements LoaderManager.LoaderCallbacks<ArrayList<Card>> {

    @InjectView(R.id.progress)
    ProgressBar progressBar;
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
//            Document doc = db.getDocument((String)((ArrayList)map.get("rows")).get(0));
//            tview.setText((String)doc.getProperty("name"));
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

        com.couchbase.lite.View view = db.getView("loadAllCards");
        view.setMap(new Mapper() {
            @Override
            public void map(Map<String, Object> stringObjectMap, Emitter emitter) {
                emitter.emit(stringObjectMap.get("name").toString(), stringObjectMap);
            }
        }, "1.0");

        if (cardsGrid.getAdapter() == null || cardsGrid.getAdapter().getCount() <= 0) {
            getActivity().getSupportLoaderManager().restartLoader(1, null, this);
        }
        return v;
    }

    @Override
    public Loader<ArrayList<Card>> onCreateLoader(int i, Bundle bundle) {
        return new CardLoader(getActivity(), db);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Card>> hashMapLoader, ArrayList<Card> cards) {
        progressBar.setVisibility(View.GONE);
        adapter = new CardsGridAdapter(cards, getActivity());
        cardsGrid.setAdapter(adapter);
        cardsGrid.setVisibility(View.VISIBLE);
        cardsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CardDetailActivity.class);
                intent.putExtra(CardDetailActivity.CARDJSON, gson.toJson(adapter.getItem(position)));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Card>> hashMapLoader) {

    }
}
