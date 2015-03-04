package com.churches.by;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.ui.Map;
import com.vla3089.functional.Receiver;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsMenu;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_churches_map)
@OptionsMenu(R.menu.menu_churches_map)
public class ChurchesMap extends ActionBarActivity implements Map.OnMapInteractionListener {

    @FragmentById(R.id.church_map)
    Map churchesMap;

    public ChurchesMap() {
    }

    /*@OptionsItem(R.id.action_settings)
    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.REQUEST_ADDRESS, "г. Минск, пл. Свободы 9");
        startService(intent);
    }*/

    @AfterViews
    protected void displayChurches() {
        DataProvider.instance().requestChurches(new Receiver<List<Church>>() {
            @Override
            public void receive(List<Church> value) {
                churchesMap.updateDisplayableChurches(new ArrayList<>(value));
            }
        });
    }

    @Override
    public void onChurchInfoClicked(Church church) {
        Toast.makeText(this, church.name() + " clicked", Toast.LENGTH_LONG).show();
    }
}
