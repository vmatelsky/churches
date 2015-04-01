package com.churches.by.helpers;

import android.test.InstrumentationTestCase;

import com.churches.by.DexCacheWorkaroundHelper;

public class TestCaseWithMockito extends InstrumentationTestCase {

    @Override
    protected void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DexCacheWorkaroundHelper.initDexCacheIfNecessary(getInstrumentation());
    }
}