package com.churches.by;

import android.content.Intent;
import android.location.Address;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.churches.by.data.DataProvider;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vla3089.functional.Receiver;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import java.util.List;

@EActivity(R.layout.activity_churches_map)
@OptionsMenu(R.menu.menu_churches_map)
public class ChurchesMap extends ActionBarActivity implements OnMapReadyCallback {

    private static final int userIcon = R.drawable.yellow_point;
    private static final int foodIcon = R.drawable.red_point;
    private static final int drinkIcon = R.drawable.blue_point;
    private static final int shopIcon = R.drawable.green_point;
    private static final int otherIcon = R.drawable.purple_point;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    public ChurchesMap() {
    }

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @OptionsItem(R.id.action_settings)
    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(FetchAddressIntentService.REQUEST_ADDRESS, "г. Минск, пл. Свободы 9");
        startService(intent);
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

        if (lastLoc == null) {
            return;
        }

        double lat = lastLoc.getLatitude();
        double lng = lastLoc.getLongitude();

        LatLng lastLatLng = new LatLng(lat, lng);

        if(userMarker!=null) {
            userMarker.remove();
        }

        Log.d("churches_map", "setting user location:" + lastLatLng.toString());

        userMarker = mMap.addMarker(new MarkerOptions()
                .position(lastLatLng)
                .title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(userIcon))
                .snippet("Your last recorded location"));

        mMap.animateCamera(CameraUpdateFactory.newLatLng(lastLatLng), 3000, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updatePlaces();
        displayChurches();

        LatLng sydney = new LatLng(-33.867, 151.206);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        mMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }
}