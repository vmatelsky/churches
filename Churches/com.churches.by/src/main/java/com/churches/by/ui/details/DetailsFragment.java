package com.churches.by.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;

import rx.Observable;
import rx.functions.Action1;

public class DetailsFragment extends Fragment {

    public static final String CHURCH_KEY = "church key";

    public static DetailsFragment newInstance(Church church) {
        final Bundle params = new Bundle();
        params.putParcelable(CHURCH_KEY, church);

        final DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(params);
        return fragment;
    }

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

            getActivity().setTitle(churchDetails.church().name());

            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new ChurchEventAdapter(churchDetails.events()));
        }
    };

    private View findViewById(int viewId) {
        return getActivity().findViewById(viewId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_details, container, false);
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

    private int textColorFromPalette(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

        if (vibrantSwatch != null) {
            return vibrantSwatch.getTitleTextColor();
        }
        return Integer.MAX_VALUE;
    }
}
