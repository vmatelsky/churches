package com.churches.by;

import android.app.Application;

public class CAppliation extends Application {
    private static CAppliation application;
    private static CLocationManager locationManager;

    public static CAppliation instance() {
        if (application == null) {
            throw new RuntimeException("Accessing application instanse before it was created");
        }

        return application;
    }

    public static CLocationManager locationManager() {
        if (locationManager == null) {
            locationManager = new UserLocationManager(instance());
        }
        return locationManager;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
