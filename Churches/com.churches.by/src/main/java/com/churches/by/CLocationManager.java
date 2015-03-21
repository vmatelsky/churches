package com.churches.by;

import android.location.Location;

public interface CLocationManager {

    public Location lastKnownLocation();

    public boolean isEnabled();

    public void startUpdating();

    public void finishUpdating();
}
