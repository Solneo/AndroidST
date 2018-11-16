package com.example.myapplicationst;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Ыщвф on 16.11.2018.
 */

public class LastSearching extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "ru.phpnick.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public LastSearching() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
