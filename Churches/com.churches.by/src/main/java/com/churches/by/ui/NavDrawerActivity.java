package com.churches.by.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.ui.drawer.DrawerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.AndroidSubscriptions;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NavDrawerActivity extends ActionBarActivity
        implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        OnChurchInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                toolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(DrawerItem drawerItem) {
        getSupportActionBar().setTitle(drawerItem.titleId());

        Fragment fragment = fragmentForDrawerItem(drawerItem);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private Fragment fragmentForDrawerItem(DrawerItem drawerItem) {
        Fragment fragment = null;

        switch (drawerItem) {
            case FAVORITES:
                fragment = ChurchListFragment.newInstance(DataProvider.instance().favoritedChurches());
                break;
            case LIST:
                fragment = ChurchListFragment.newInstance(DataProvider.instance().churches());
                break;
            case MAP:
                fragment = Map.newInstance(new ArrayList<Church>());
                break;
            case SETTINGS:
                fragment = SettingsFragment.newInstance();
                break;
            case ABOUT:
                fragment = AboutFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_churches_map, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Toast.makeText(this, item.getTitle() + " touched", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChurchClicked(Church church) {
        Toast.makeText(this, church.name() + " touched", Toast.LENGTH_LONG).show();
    }
}
