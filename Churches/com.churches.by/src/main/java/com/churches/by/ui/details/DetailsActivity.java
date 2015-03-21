package com.churches.by.ui.details;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.model.Church;

public class DetailsActivity extends ActionBarActivity {

    public static final String CHURCH_KEY = "church key";

    private Church church;

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

        Palette generate = Palette.generate(church.smallIcon());
        findViewById(R.id.church_image_frame).setBackgroundColor(generate.getLightMutedSwatch().getRgb());

        ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
        imageView.setImageBitmap(church.smallIcon());

        TextView textView = (TextView) findViewById(R.id.parish_event_message);
        textView.setTextColor(generate.getVibrantSwatch().getTitleTextColor());

        setTitle(church.name());
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
