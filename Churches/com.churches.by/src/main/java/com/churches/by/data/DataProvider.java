package com.churches.by.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.churches.by.CAppliation;
import com.churches.by.R;
import com.churches.by.data.model.Address;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.List;

public class DataProvider {

    private static DataProvider instance = new DataProvider();

    public static DataProvider instance() {
        return instance;
    }

    private DataProvider() {

    }

    public List<Church> churches() {
        // Катедра
//        address.setLatitude(53.9031514);
//        address.setLongitude(27.5546604);

        Address address = new Address("Беларусь", "Борисов", " ", new LatLng(54.2417, 28.5056));
        Bitmap image = BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov1);
        Church church = new Church(image, "Костёл Рождества Пресвятой Девы Марии", address);

        return Arrays.asList(church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church,
                church);
    }

    public List<Church> favoritedChurches() {
        return churches();
    }

}
