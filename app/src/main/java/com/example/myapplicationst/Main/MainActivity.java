package com.example.myapplicationst.Main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationst.App.AppNetCom;
import com.example.myapplicationst.Fragment.MainList;
import com.example.myapplicationst.LayoutActivity.CreateNewObj.CreateNewObjActivity;
import com.example.myapplicationst.LayoutActivity.ListObjekt;
import com.example.myapplicationst.LayoutActivity.LoginActivity;
import com.example.myapplicationst.LayoutActivity.MyAccountActivity;
import com.example.myapplicationst.LayoutActivity.SearchActivity;
import com.example.myapplicationst.QrReader.BARReader;
import com.example.myapplicationst.R;
import com.example.myapplicationst.UtilForDataSave.PrefSaver;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment = null;
    private Class fragmentClass = null;
    private PrefSaver prefSaver;
    private ThemeChUt themeChUt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (fragmentClass == null) {
            fragmentClass = MainList.class;
            setFragment();
            setFragmentMenegerAndReplFragment();
            ThemeUtils.onActivityCreateSetTheme(this);
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

        authChekAndBind();
        // drawer.openDrawer(GravityCompat.START);//покажем открытую шторку при первом запуске
    }

    private void changeTheme() {
        themeChUt = new ThemeChUt();

    }

    void authChekAndBind() {
        PrefSaver prefSaver = new PrefSaver(this);
        UserDataM userDataM = new UserDataM(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView textName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.text_main_name);
        TextView textEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.text_main_email);
        ImageView imageAvatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.image_avatar);
        MenuItem textAccOrLogin = (MenuItem) navigationView.getMenu().findItem(R.id.nav_account);
       /* ImageView ico = findViewById(R.id.ic)
        imgshare = (Imageview) findviewbyId(R.id.imageshare);
        imgshare.setColorFilter(color);*///TODO сделать темы темную и светлую, ф здесь иконку менять
        if (prefSaver.loadData("auth") != null) {
            Log.i("myerr", prefSaver.loadData("auth"));
            textName.setText(userDataM.getUsername());
            textEmail.setText(userDataM.getEmail());
            AppNetCom.setAuth(true);
            AppNetCom.setStringToken(prefSaver.loadData("token"));
            AppNetCom.setStringCookie(prefSaver.loadData("cookie"));
            textAccOrLogin.setTitle("Мой аккаунт");
            setAva(imageAvatar, userDataM.getUsername());
        } else {
            textAccOrLogin.setTitle("Вход или авторизация");
            textName.setText("Неавторизованный пользователь");
            textEmail.setText("nothing@mail.com");
        }
    }

    private void setAva(ImageView v, String s) {
        int COVER_IMAGE_SIZE = 200; //in pixels
        LetterBitmap letterBitmap = new LetterBitmap(this);
        Bitmap letterTile = letterBitmap.getLetterTile(s, s, COVER_IMAGE_SIZE, COVER_IMAGE_SIZE);
        v.setImageBitmap(letterTile);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        authChekAndBind();
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

        } else if (id == R.id.nav_object_list) {
            goToActivity(this, ListObjekt.class);

        } else if (id == R.id.nav_account) {
            if (AppNetCom.getAuth()) {
                goToActivity(this, MyAccountActivity.class);
            } else {
                goToActivity(this, LoginActivity.class);
            }
        } else if (id == R.id.nav_search) {
            goToActivity(this, SearchActivity.class);
        } else if (id == R.id.nav_favorites) {
            goToActivity(this, ListObjekt.class);
        } else if (id == R.id.nav_messages) {
            goToActivity(this, CreateNewObjActivity.class);
        } else if (id == R.id.nav_setting) {
            goToActivity(this, CreateNewObjActivity.class);
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

    private void setFragmentMenegerAndReplFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void fragmentItemDr() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void goToActivity(Context context, Class clas) {
        Intent intent = new Intent(context, clas);
        startActivity(intent);
    }

    public void goToQRReaderActivity(View v) {
        Intent intent = new Intent(this, BARReader.class);
        startActivity(intent);
    }


    public void setDarckTheme(View v) {
        ThemeUtils.changeToTheme(this, 0);
    }

    public void setLightTheme(View v) {
        ThemeUtils.changeToTheme(this, 1);
    }
}
