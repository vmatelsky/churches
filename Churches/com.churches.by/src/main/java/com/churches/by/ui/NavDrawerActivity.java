package com.churches.by.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.ui.drawer.DrawerItem;
import com.churches.by.ui.drawer.DrawerRow;

import java.util.ArrayList;

public class NavDrawerActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        ChurchListFragment.OnChurchListInteractionListener {

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
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ChurchListFragment.newInstance())
                .commit();
    }

    @Override
    public DrawerItem[] drawerItems() {
        ArrayList<DrawerItem> items = new ArrayList<>();

        items.add(new DrawerRow(R.drawable.ic_drawer, getString(R.string.nav_drawer_favorites)));
        items.add(new DrawerRow(R.drawable.ic_drawer, getString(R.string.nav_drawer_list)));
        items.add(new DrawerRow(R.drawable.ic_drawer, getString(R.string.nav_drawer_map)));
        items.add(new DrawerRow(R.drawable.ic_drawer, getString(R.string.nav_drawer_settings)));
        items.add(new DrawerRow(R.drawable.ic_drawer, getString(R.string.nav_drawer_about)));

        return items.toArray(new DrawerItem[0]);
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
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, id + " touched", Toast.LENGTH_LONG).show();
    }
}
