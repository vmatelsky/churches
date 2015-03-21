package com.churches.by.ui.details;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;

import rx.Observable;
import rx.functions.Action1;

public class DetailsActivity extends ActionBarActivity {

    public static final String CHURCH_KEY = "church key";

    private Church church;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(CHURCH_KEY)) {
            church = extras.getParcelable(CHURCH_KEY);
        } else {
            Toast.makeText(this, R.string.no_church_passed, Toast.LENGTH_LONG).show();
            onBackPressed();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.church_events_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);


        Observable.OnSubscribe<ChurchDetails> detailsOnSubscribe = DataProvider.instance().churchDetails(church);

        Observable.create(detailsOnSubscribe)
                .subscribe(new Action1<ChurchDetails>() {
                    @Override
                    public void call(ChurchDetails churchDetails) {
                        Palette generate = Palette.generate(churchDetails.image());
                        findViewById(R.id.church_image_frame).setBackgroundColor(generate.getLightMutedSwatch().getRgb());

                        ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
                        imageView.setImageBitmap(churchDetails.image());

                        TextView textView = (TextView) findViewById(R.id.parish_event_message);
                        textView.setTextColor(generate.getVibrantSwatch().getTitleTextColor());

                        setTitle(churchDetails.church().name());

                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
