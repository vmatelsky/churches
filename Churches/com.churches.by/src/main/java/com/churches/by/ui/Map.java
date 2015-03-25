package com.churches.by.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class Map extends Fragment implements OnMapReadyCallback {

    private static final int churchIcon = R.drawable.purple_point;
    private static final String ARG_DISPLAYABLE_CHURCHES = "displayable churches";

    private List<Church> displayableChurches;
    private Action1<List<Church>> churchesObtainAction = new Action1<List<Church>>() {
        @Override
        public void call(List<Church> churches) {
            displayableChurches = churches;

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.getMapAsync(Map.this);
            }
        }
    };

    private OnChurchInteractionListener mListener;

    private GoogleMap mMap;

    public static Map newInstance() {
        Map fragment = new Map();
        return fragment;
    }

    public Map() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            displayableChurches = savedInstanceState.getParcelableArrayList(ARG_DISPLAYABLE_CHURCHES);
            churchesObtainAction.call(displayableChurches);
        } else {
            Observable.OnSubscribe<List<Church>> churchesObserver = DataProvider.instance().churches();
            Observable.create(churchesObserver).subscribe(churchesObtainAction);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(ARG_DISPLAYABLE_CHURCHES, displayableChurches.toArray(new Church[displayableChurches.size()]));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChurchInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChurchInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void displayChurchesOnMap(List<Church> displayableChurches) {
         for (Church church : displayableChurches) {
            mMap.addMarker(new MarkerOptions()
                    .position(church.latLng())
                    .icon(BitmapDescriptorFactory.fromResource(churchIcon))
                    .title(church.name()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        displayChurchesOnMap(this.displayableChurches);
        mMap.setMyLocationEnabled(true);
     }

}
