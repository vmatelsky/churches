package com.churches.by;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.vla3089.functional.Receiver;
import com.vla3089.functional.ReceiverSubscription;

public class UserLocationManager implements CLocationManager, LocationListener {

    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final int MIN_DISTANCE = 0;

    private final LocationManager locationManager;
    private final ReceiverSubscription<Location> locationReceiverSubscription = new ReceiverSubscription<>();

    public UserLocationManager(Context context) {
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public Location lastKnownLocation() {
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void addNewLocationReceiver(Receiver<Location> onNewLocation) {
        locationReceiverSubscription.subscribe(onNewLocation);
    }

    @Override
    public void removeNewLocationReceiver(Receiver<Location> onNewLocation) {
        locationReceiverSubscription.unSubscribe(onNewLocation);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void startUpdating() {
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MILLISECONDS_PER_SECOND,
                MIN_DISTANCE,
                this);
    }

    @Override
    public void finishUpdating() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        locationReceiverSubscription.receive(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
