package com.churches.by.ui.churcheslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.receivers.ChurchesReceiver;
import com.churches.by.ui.OnChurchInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class ChurchListFragment extends Fragment implements ChurchListItemViewHolder.OnClickListener {

    public static final String CHURCHES_LIST_KEY = "churches list key";

    private OnChurchInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private List<Church> churchesList = new ArrayList<>();
    private ChurchesReceiver churchesObtainAction = new ChurchesReceiver() {
        @Override
        public void call(List<Church> churches) {
            churchesList = churches;
            RecyclerView.Adapter mAdapter = new ChurchesAdapter(churchesList, ChurchListFragment.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    };

    public static ChurchListFragment newInstance() {
        ChurchListFragment fragment = new ChurchListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ChurchListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_church, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.churches_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (savedInstanceState != null) {
            churchesList = savedInstanceState.getParcelableArrayList(CHURCHES_LIST_KEY);
            churchesObtainAction.call(churchesList);
        } else {
            DataProvider.instance().churchesAsync(churchesObtainAction);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_churches_map, menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(CHURCHES_LIST_KEY, churchesList.toArray(new Church[churchesList.size()]));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChurchInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnChurchInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onChurchClicked(Church church) {
        if (mListener != null) {
            mListener.onChurchClicked(church);
        }
    }
}
