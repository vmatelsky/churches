package com.churches.by;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class UserLocationManager implements CLocationManager {

    private final LocationManager locationManager;

    public UserLocationManager(Context context) {
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public Location lastKnownLocation() {
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }
}
