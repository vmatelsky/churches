package com.churches.by.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.churches.by.R;
import com.churches.by.data.model.Church;
import com.churches.by.ui.churcheslist.ChurchListFragment;
import com.churches.by.ui.details.DetailsActivity;

public class MainActivity extends AppCompatActivity
        implements OnChurchInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ChurchListFragment fragment = ChurchListFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onChurchClicked(final Church church) {
        Intent detailsActivityIntent = new Intent(this, DetailsActivity.class);
        detailsActivityIntent.putExtra(DetailsActivity.CHURCH_ID_KEY, church.id());
        startActivity(detailsActivityIntent);
    }

    @Override
    public void onSettingsClicked() {
        new AboutDialog().show(getSupportFragmentManager(), "about");
    }

}
