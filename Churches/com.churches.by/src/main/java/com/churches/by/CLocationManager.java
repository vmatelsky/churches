package com.churches.by;

import android.location.Location;

import com.vla3089.functional.Receiver;

public interface CLocationManager {

    public Location lastKnownLocation();

    public void addNewLocationReceiver(Receiver<Location> newLocation);

    public void removeNewLocationReceiver(Receiver<Location> newLocation);

    public boolean isEnabled();

    public void startUpdating();

    public void finishUpdating();
}
