package com.churches.by.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.churches.by.R;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends Fragment implements OnMapReadyCallback {

    private static final int churchIcon = R.drawable.purple_point;
    private static final String ARG_DISPLAYABLE_CHURCHES = "displayable churches";

    private ArrayList<Church> displayableChurches;

    private OnMapInteractionListener mListener;

    private GoogleMap mMap;

    public static Map newInstance(ArrayList<Church> displayableChurches) {
        Map fragment = new Map();
        fragment.setArguments(createArguments(displayableChurches));
        return fragment;
    }

    public static Bundle createArguments(ArrayList<Church> displayableChurches) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_DISPLAYABLE_CHURCHES, displayableChurches);
        return args;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            displayableChurches = getArguments().getParcelableArrayList(ARG_DISPLAYABLE_CHURCHES);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMapInteractionListener) activity;

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if(mapFragment != null) {
                mapFragment.getMapAsync(this);
            }

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMapInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateDisplayableChurches(ArrayList<Church> displayableChurches) {
        this.displayableChurches = displayableChurches;

        if (mMap != null) {
            mMap.clear();
            displayChurchesOnMap(this.displayableChurches);
        }
    }

    private void displayChurchesOnMap(ArrayList<Church> displayableChurches) {
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
        
        LatLng sydney = new LatLng(-33.867, 151.206);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        mMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
     }

    public interface OnMapInteractionListener {
        public void onChurchInfoClicked(Church church);
    }

}
