package com.example.myapplicationst.LayoutActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;

import com.example.myapplicationst.LastSearching;

/**
 * Created by Ыщвф on 16.11.2018.
 */

public class SearchActivity extends Activity {
    public void qwe() {
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    LastSearching.AUTHORITY, LastSearching.MODE);
            suggestions.saveRecentQuery(query, null);
        }
    }
}
