package com.churches.by.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.churches.by.R;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.ui.churcheslist.ChurchListFragment;
import com.churches.by.ui.details.DetailsFragment;
import com.churches.by.ui.schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity
        implements OnChurchInteractionListener,
        DetailsFragment.Listener {

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
    public void onChurchClicked(Church church) {
        final DetailsFragment fragment = DetailsFragment.newInstance(church);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onSettingsClicked() {
        new AboutDialog().show(getSupportFragmentManager(), "about");
    }

    @Override
    public void onScheduleClicked(ChurchDetails details) {
        final ScheduleFragment fragment = ScheduleFragment.newInstance(details);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("")
                .commit();
    }
}
