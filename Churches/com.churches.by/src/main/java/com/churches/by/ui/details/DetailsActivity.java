package com.churches.by.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.ChurchDetails;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import rx.functions.Action1;

public class DetailsActivity extends AppCompatActivity {

    public static final String CHURCH_ID_KEY = "church key";

    private RecyclerView recyclerView;
    public ChurchDetails churchDetails;
    private final Action1<ChurchDetails> detailsObtainedAction = new Action1<ChurchDetails>() {

        @Override
        public void call(final ChurchDetails churchDetails) {
            DetailsActivity.this.churchDetails = churchDetails;

            ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
            imageView.setImageBitmap(churchDetails.image());

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
//            recyclerView.setAdapter(new ScheduleItemAdapter(churchDetails.scheduleItems()));
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        recyclerView = (RecyclerView) findViewById(R.id.church_events_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final long churchId = getIntent().getLongExtra(CHURCH_ID_KEY, -1);
        DataProvider.instance().churchDetailsByChurchId(churchId)
                .subscribe(detailsObtainedAction);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == R.id.show_on_map) {
            showOnMap();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showOnMap() {
//        Address address = churchDetails.church().address();
//        String uri = "geo:0,0?q=" + address.town() + "+" + address.street();
        LatLng latLng = churchDetails.church().latLng();
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latLng.latitude, latLng.longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_application_can_handle_map_request, Toast.LENGTH_LONG).show();
        }
    }
}
