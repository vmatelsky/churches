package com.churches.by.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.churches.by.R;
import com.churches.by.ui.drawer.DrawerItem;
import com.churches.by.ui.drawer.DrawerListAdapter;

public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private NavigationDrawerCallbacks mCallbacks;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private View mDrawerView;


    public NavigationDrawerFragment() {
        // FIXME: on app start supportActionBar title is not changed
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mDrawerListView = (ListView) mDrawerView.findViewById(R.id.drawer_items_list_view);
        mDrawerListView.setOnItemClickListener(this);

        mDrawerListView.setAdapter(new DrawerListAdapter(getActionBarActivity(), DrawerItem.values()));
        return mDrawerView;
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActionBarActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(getActionBarActivity(), mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    private void notifyCallback(DrawerItem drawerItem) {
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(drawerItem);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionBarActivity getActionBarActivity() {
        return (ActionBarActivity)getActivity();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerListView.setItemChecked(position, true);
        mCurrentSelectedPosition = position;
        notifyCallback((DrawerItem) mDrawerListView.getItemAtPosition(position));
        closeDrawer();
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(DrawerItem drawerItem);
    }
}
