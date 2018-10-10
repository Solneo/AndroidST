package com.example.myapplicationst;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplicationst.Fragment.BuildingList;
import com.example.myapplicationst.Fragment.MainList;
import com.example.myapplicationst.Fragment.QRReaderFragment;
import com.example.myapplicationst.Fragment.Tools;
import com.example.myapplicationst.QrReader.BARReader;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment = null;
    private Class fragmentClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (fragmentClass == null) {
            fragmentClass = MainList.class;
            setFragment();
            setFragmentMenegerAndReplFragment();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Псс, оно не работает", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);//Тут происходит инициализация шторки
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // drawer.openDrawer(GravityCompat.START);//покажем открытую шторку при первом запуске
    }

    @Override
    public void onBackPressed() {//Метод который закрывает шторку при нажатии кнопки назад
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Добавляем эдементы навигации для возможности кликов по ним
        int id = item.getItemId();


        if (id == R.id.nav_main) {
            // Действие при нажатии на иконку камеры импорт
            fragmentClass = MainList.class;

        } else if (id == R.id.nav_object) {
            goToObjektActivity();

        } else if (id == R.id.nav_account) {
            goToQRReaderActivity();
        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_messages) {

        } else if (id == R.id.nav_setting) {
            fragmentClass = Tools.class;
        }
        setFragment();
        fragmentItemDr();
        setFragmentMenegerAndReplFragment();
        item.setChecked(true);
        setTitle(item.getTitle());
        return true;
    }

    public void setFragment() {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFragmentMenegerAndReplFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void fragmentItemDr() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void goToNewActivity(View v) {
        Intent intent = new Intent(this, OneObjekt.class);
        startActivity(intent);
    }

    public void goToObjektActivity() {
        Intent intent = new Intent(this, OneObjekt.class);
        startActivity(intent);
    }

    public void goToQRReaderActivity(View v) {
        Intent intent = new Intent(this, BARReader.class);
        startActivity(intent);
    }

    public void goToQRReaderActivity() {
        Intent intent = new Intent(this, BARReader.class);
        startActivity(intent);
    }
}
