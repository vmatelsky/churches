package com.churches.by.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.ui.schedule.ScheduleItemAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.functions.Action1;

public class DetailsFragment extends Fragment {

    public static final String CHURCH_KEY = "church key";

    public interface Listener {
    }

    public static DetailsFragment newInstance(Church church) {
        final Bundle params = new Bundle();
        params.putParcelable(CHURCH_KEY, church);

        final DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(params);
        return fragment;
    }

    private RecyclerView recyclerView;
    public ChurchDetails churchDetails;
    private Listener listener;

    List<View> tabs = new ArrayList<>();

    private final Action1<ChurchDetails> detailsObtainedAction = new Action1<ChurchDetails>() {

        @Override
        public void call(final ChurchDetails churchDetails) {
            DetailsFragment.this.churchDetails = churchDetails;

            ImageView imageView = (ImageView) findViewById(R.id.detailed_church_image);
            imageView.setImageBitmap(churchDetails.image());

            final View events = findViewById(R.id.parish_event_message);
            final View schedule = findViewById(R.id.schedule_items_message);

            final View.OnClickListener eventsClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOnly(view);
                    recyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
                }
            };
            events.setOnClickListener(eventsClickListener);
            schedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectOnly(view);
                    recyclerView.setAdapter(new ScheduleItemAdapter(churchDetails.scheduleItems()));
                }
            });

            tabs.add(events);
            tabs.add(schedule);

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            eventsClickListener.onClick(events);
        }
    };

    private void selectOnly(View certainView) {
        for (View view: tabs) {
            view.setSelected(view == certainView);
        }
    }

    private View findViewById(int viewId) {
        return getActivity().findViewById(viewId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (Listener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.church_events_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final Church church = getArguments().getParcelable(CHURCH_KEY);
        Observable.OnSubscribe<ChurchDetails> detailsOnSubscribe = DataProvider.instance().churchDetails(church);
        Observable.create(detailsOnSubscribe).subscribe(detailsObtainedAction);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_activity_menu, menu);
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

        if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), R.string.no_application_can_handle_map_request, Toast.LENGTH_LONG).show();
        }
    }

}
