package com.churches.by.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;

public class ChurchListFragment extends Fragment {

    private OnChurchInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChurchesAdapter(DataProvider.instance().churches(), new ChurchesAdapter.ViewHolder.ChurchesViewHolderClicks() {
            @Override
            public void onChurchClicked(Church church) {
                if (mListener != null) {
                    mListener.onChurchClicked(church);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
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

}
