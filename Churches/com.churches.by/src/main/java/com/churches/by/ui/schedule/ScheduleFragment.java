package com.churches.by.ui.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.ChurchDetails;

public class ScheduleFragment extends Fragment {

    public static final String DETAILS_KEY = "details key";

    public static ScheduleFragment newInstance(ChurchDetails details) {
        final Bundle params = new Bundle();
        params.putParcelable(DETAILS_KEY, details);

        final ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(params);
        return fragment;
    }

    public ScheduleFragment() {

    }

    private RecyclerView recyclerView;

    private View findViewById(int viewId) {
        return getActivity().findViewById(viewId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.schedule_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final ChurchDetails church = getArguments().getParcelable(DETAILS_KEY);
        if (church != null) {
            recyclerView.setAdapter(new ScheduleItemAdapter(church.scheduleItems()));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_activity_menu, menu);
    }

}
