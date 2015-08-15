package com.churches.by;

import android.app.Application;

public class CAppliation extends Application {
    private static CAppliation application;

    public static CAppliation instance() {
        if (application == null) {
            throw new RuntimeException("Accessing application instanse before it was created");
        }

        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
