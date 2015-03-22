package com.churches.by.ui.details;

import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.ui.ToolbarActivity;

import rx.Observable;
import rx.functions.Action1;

public class DetailsActivity extends ToolbarActivity {

    public static final String CHURCH_KEY = "church key";

    private Church church;
    private RecyclerView recyclerView;

    private final Action1<ChurchDetails> detailsObtainedAction = new Action1<ChurchDetails>() {
        @Override
        public void call(ChurchDetails churchDetails) {
            Palette generate = Palette.generate(churchDetails.image());
            findViewById(R.id.church_image_frame).setBackgroundColor(generate.getLightMutedSwatch().getRgb());

            ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
            imageView.setImageBitmap(churchDetails.image());

            TextView textView = (TextView) findViewById(R.id.parish_event_message);

            Palette.Swatch vibrantSwatch = generate.getVibrantSwatch();
            if (vibrantSwatch != null) {
                textView.setTextColor(vibrantSwatch.getTitleTextColor());
            }

            setTitle(churchDetails.church().name());

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(CHURCH_KEY)) {
            church = extras.getParcelable(CHURCH_KEY);
        } else {
            Toast.makeText(this, R.string.no_church_passed, Toast.LENGTH_LONG).show();
            onBackPressed();
        }

        recyclerView = (RecyclerView) findViewById(R.id.church_events_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Observable.OnSubscribe<ChurchDetails> detailsOnSubscribe = DataProvider.instance().churchDetails(church);
        Observable.create(detailsOnSubscribe).subscribe(detailsObtainedAction);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }
}
