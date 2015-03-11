package com.churches.by.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.Church;

import java.util.ArrayList;
import java.util.List;

public class ChurchListFragment extends Fragment {

    public static final String CHURCHES_LIST_KEY = "churches list key";

    private OnChurchInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private List<Church> churchesList = new ArrayList<>();

    public static ChurchListFragment newInstance(List<Church> churches) {
        ChurchListFragment fragment = new ChurchListFragment();
        Bundle args = new Bundle();
        ArrayList<Church> arrayList = new ArrayList<>(churches);
        args.putParcelableArrayList(CHURCHES_LIST_KEY, arrayList);
        fragment.setArguments(args);
        return fragment;
    }

    public ChurchListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CHURCHES_LIST_KEY)) {
            churchesList = getArguments().getParcelableArrayList(CHURCHES_LIST_KEY);
        }
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

        RecyclerView.Adapter mAdapter = new ChurchesAdapter(churchesList, new ChurchesAdapter.ViewHolder.ChurchesViewHolderClicks() {
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
