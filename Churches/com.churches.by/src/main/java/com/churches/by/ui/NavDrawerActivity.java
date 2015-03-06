package com.churches.by.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

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

        items.add(new DrawerRow(R.drawable.ic_drawer, "section 1"));
        items.add(new DrawerRow(R.drawable.ic_drawer, "section 2"));
        items.add(new DrawerRow(R.drawable.ic_drawer, "section 3"));
        items.add(new DrawerRow(R.drawable.ic_drawer, "section 4"));
        items.add(new DrawerRow(R.drawable.ic_drawer, "section 5"));
        items.add(new DrawerRow(R.drawable.ic_drawer, "section 6"));

        return items.toArray(new DrawerItem[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_churches_map, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
