package com.churches.by;

import android.app.Instrumentation;

public class DexCacheWorkaroundHelper {
    private static boolean sNeedToSetDexCacheSet = true;

    public static void initDexCacheIfNecessary(Instrumentation instrumentation){
        if(sNeedToSetDexCacheSet) {
            System.setProperty("dexmaker.dexcache", instrumentation.getTargetContext().getCacheDir().getPath());
            sNeedToSetDexCacheSet = false;
        }
    }
}