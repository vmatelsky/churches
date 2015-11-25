package com.churches.by.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.receivers.ChurchesReceiver;
import com.churches.by.ui.churcheslist.ChurchListItemViewHolder;
import com.churches.by.ui.churcheslist.ChurchesAdapter;
import com.churches.by.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class ChurchesListActivity extends AppCompatActivity implements ChurchListItemViewHolder.OnClickListener {

    public static final String CHURCHES_LIST_KEY = "churches list key";

    private RecyclerView mRecyclerView;

    private List<Church> churchesList = new ArrayList<>();
    private ChurchesReceiver churchesObtainAction = new ChurchesReceiver() {
        @Override
        public void call(List<Church> churches) {
            churchesList = churches;
            RecyclerView.Adapter mAdapter = new ChurchesAdapter(churchesList, ChurchesListActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_churches_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                                       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show());


        mRecyclerView = (RecyclerView) findViewById(R.id.churches_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (savedInstanceState != null) {
            churchesList = savedInstanceState.getParcelableArrayList(CHURCHES_LIST_KEY);
            churchesObtainAction.call(churchesList);
        } else {
            DataProvider.instance().churchesAsync().subscribe(churchesObtainAction);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_churches_map, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(CHURCHES_LIST_KEY, churchesList.toArray(new Church[churchesList.size()]));
    }

    @Override
    public void onChurchClicked(final Church church) {
        Intent detailsActivityIntent = new Intent(this, DetailsActivity.class);
        detailsActivityIntent.putExtra(DetailsActivity.CHURCH_ID_KEY, church.id());
        startActivity(detailsActivityIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            onSettingsClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSettingsClicked() {
        new AboutDialog().show(getSupportFragmentManager(), "about");
    }
}
