package com.churches.by;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vla3089.functional.Receiver;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

import java.util.List;

@EActivity(R.layout.activity_churches_map)
@OptionsMenu(R.menu.menu_churches_map)
public class ChurchesMap extends ActionBarActivity {

    private int userIcon, foodIcon, drinkIcon, shopIcon, otherIcon;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker userMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userIcon = R.drawable.yellow_point;
        foodIcon = R.drawable.red_point;
        drinkIcon = R.drawable.blue_point;
        shopIcon = R.drawable.green_point;
        otherIcon = R.drawable.purple_point;

        if(mMap==null){
            FragmentManager fm = getSupportFragmentManager();
            SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

            if (mapFragment != null) {
                mMap = mapFragment.getMap();
            }
        }

        if (mMap != null) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            updatePlaces();
            displayChurches();
        }
    }

    private void displayChurches() {
        DataProvider.instance().requestChurches(new Receiver<List<Church>>() {
            @Override
            public void receive(List<Church> value) {
                for (Church church : value) {
                    Address address = church.address();
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(otherIcon))
                    .title(church.name()));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updatePlaces(){
        Location lastLoc = CAppliation.locationManager().lastKnownLocation();

        double lat = lastLoc.getLatitude();
        double lng = lastLoc.getLongitude();

        LatLng lastLatLng = new LatLng(lat, lng);

        if(userMarker!=null) {
            userMarker.remove();
        }

        userMarker = mMap.addMarker(new MarkerOptions()
                .position(lastLatLng)
                .title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(userIcon))
                .snippet("Your last recorded location"));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(lastLatLng), 3000, null);
    }
}
