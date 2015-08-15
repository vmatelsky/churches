package com.churches.by.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.model.Church;
import com.churches.by.ui.churcheslist.ChurchListFragment;
import com.churches.by.ui.details.DetailsActivity;
import com.churches.by.ui.drawer.DrawerItem;
import com.churches.by.ui.drawer.NavigationDrawerFragment;

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

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout)
        );
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
                fragment = ChurchListFragment.newInstance();
                break;
            case LIST:
                fragment = ChurchListFragment.newInstance();
                break;
            case MAP:
                fragment = Map.newInstance();
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
        Intent intent = new Intent(NavDrawerActivity.this, DetailsActivity.class);
        Bundle b = new Bundle();
        b.putParcelable(DetailsActivity.CHURCH_KEY, church);
        intent.putExtras(b);
        startActivity(intent);
    }
}
