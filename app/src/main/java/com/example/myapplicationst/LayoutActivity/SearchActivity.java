package com.example.myapplicationst.LayoutActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.TextView;

import com.example.myapplicationst.LastSearching;
import com.example.myapplicationst.R;

/**
 * Created by Ыщвф on 16.11.2018.
 */

public class SearchActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_list);
        qwe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        return true;
    }

    public void qwe() {
        Intent intent = getIntent();
        textView = findViewById(R.id.textView5);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            textView.setText(query);

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    LastSearching.AUTHORITY, LastSearching.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }
}
