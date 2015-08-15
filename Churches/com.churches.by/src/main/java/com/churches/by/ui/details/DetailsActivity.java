package com.churches.by.ui.details;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;

import rx.Observable;
import rx.functions.Action1;

public class DetailsActivity extends AppCompatActivity {

    public static final String CHURCH_KEY = "church key";

    private Church church;
    private RecyclerView recyclerView;

    private final Action1<ChurchDetails> detailsObtainedAction = new Action1<ChurchDetails>() {
        @Override
        public void call(ChurchDetails churchDetails) {
            Palette generate = Palette.generate(churchDetails.image());

            CardView imageFrame = (CardView) findViewById(R.id.church_image_frame);
            imageFrame.setCardBackgroundColor(generate.getLightMutedSwatch().getRgb());

            ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
            imageView.setImageBitmap(churchDetails.image());

            TextView textView = (TextView) findViewById(R.id.parish_event_message);

            textView.setTextColor(textColorFromPalette(generate));

            setTitle(churchDetails.church().name());

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
        }
    };

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

        recyclerView = (RecyclerView) findViewById(R.id.church_events_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Observable.OnSubscribe<ChurchDetails> detailsOnSubscribe = DataProvider.instance().churchDetails(church);
        Observable.create(detailsOnSubscribe).subscribe(detailsObtainedAction);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private int textColorFromPalette(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        Palette.Swatch vibrantLightSwatch = palette.getVibrantSwatch();

        if (vibrantSwatch != null) {
            return vibrantSwatch.getTitleTextColor();
        } else if (vibrantLightSwatch != null) {
            return vibrantLightSwatch.getTitleTextColor();
        }
        return Integer.MAX_VALUE;
    }
}
