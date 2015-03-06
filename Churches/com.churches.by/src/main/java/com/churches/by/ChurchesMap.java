package com.churches.by;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.churches.by.data.model.Church;
import com.churches.by.ui.BaseActivity;
import com.churches.by.ui.Map;

public class ChurchesMap extends BaseActivity implements Map.OnMapInteractionListener, Toolbar.OnMenuItemClickListener {

    private DrawerLayout drawer;

    public ChurchesMap() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBarIcon(R.drawable.ic_drawer);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_churches_map, menu);
        return true;
    }

    @Override
    public void onChurchInfoClicked(Church church) {
        Toast.makeText(this, church.name() + " clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this, menuItem.getTitle() + " clicked", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_churches_map;
    }
}
