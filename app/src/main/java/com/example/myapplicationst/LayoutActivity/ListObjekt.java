package com.example.myapplicationst.LayoutActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.LastSearching;
import com.example.myapplicationst.Main.MainActivity;
import com.example.myapplicationst.Main.ThemeChUt;
import com.example.myapplicationst.Main.ThemeUtils;
import com.example.myapplicationst.NetCommunication.Adapters.AdapterResponse;
import com.example.myapplicationst.NetCommunication.AdditionalIntetrfaces.RecyclerViewClickListener;
import com.example.myapplicationst.NetCommunication.Models.PostModel;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.DataSaver;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ыщвф on 28.09.2018.
 */

public class ListObjekt extends AppCompatActivity implements RecyclerViewClickListener {
    RecyclerView recyclerView;
    List<PostModel> posts = new ArrayList<>();
    AdapterResponse adapter;
    Context context;
    DataSaver dataSaver;
    ThemeChUt themeChUt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(AppNetCom.getMyTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_objekt);
        ThemeUtils.onActivityCreateSetTheme(this);
        themeChUt = new ThemeChUt();
        startResponse();
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
        super.onNewIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            //тут отправлять поисковый запрос надо не забыть

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    LastSearching.AUTHORITY, LastSearching.MODE);
            suggestions.saveRecentQuery(query, null);
        }
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

    /* public void saveData() {
         try {
             SQLiteDatabase myDB =
                     openOrCreateDatabase("my.db", MODE_PRIVATE, null);
             myDB.close();
             dataSaver = new DataSaver(myDB, this);
         } catch (Exception e) {
             Log.i("myerrorDataSaver:", e.getMessage());
         }
     }*/
    private void bindItem() {

    }

    public void onBackPressedButton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /* public void onButtonStartResponce(View v) {
         startResponse();
     }*/
    @Override
    public void recyclerViewListClicked(View v, int position, String str) {
        AppNetCom appNetCom = (AppNetCom) getApplication();
        ((AppNetCom) getApplication()).setStringId(str);
        Intent intent = new Intent(this, OneObject.class);
        startActivity(intent);
    }


    public void startResponse() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        themeChUt.setMyThemeToRec(recyclerView);
        adapter = new AdapterResponse(posts, context, this);
        recyclerView.setAdapter(adapter);

      /*  try {
            Response response = AppNetCom.getApi().getData("app",50).execute();
        } catch (Exception e) {

        }*/

        AppNetCom.getApi().getData().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.body() != null) {
                    posts.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    okhttp3.Request request;
                    request = call.request();
                    Log.i("qwe", request.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(ListObjekt.this, "Чет, поломалось...", Toast.LENGTH_SHORT).show();
                Log.i("sdf", t.getMessage());
                okhttp3.Request request;
                request = call.request();
                Log.i("qwe", request.toString());
            }
        });

    }

    public void goToOneObjectWithID(View v) {

       /*recyclerView.
        AppNetCom appNetCom = (AppNetCom) getApplication();
        ((AppNetCom) getApplication()).setStringId();
        Intent intent = new Intent(this, OneObject.class);
        startActivity(intent);*/
    }
}
