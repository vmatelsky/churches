package com.churches.by.data.dummy;

import android.graphics.BitmapFactory;

import com.churches.by.CAppliation;
import com.churches.by.R;
import com.churches.by.data.model.ChurchDetails;

import java.util.Arrays;

public class DummyChurchDetails {

    public static ChurchDetails Borisov = new ChurchDetails(
            DummyChurch.Borisov,
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov2),
            Arrays.asList(DummyChurchEvent.Dummy, DummyChurchEvent.Dummy, DummyChurchEvent.Dummy),
            "");

    public static ChurchDetails Katedra = new ChurchDetails(
            DummyChurch.Katedra,
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.katedra_big),
            Arrays.asList(DummyChurchEvent.Dummy, DummyChurchEvent.Dummy, DummyChurchEvent.Dummy),
            "");

}
