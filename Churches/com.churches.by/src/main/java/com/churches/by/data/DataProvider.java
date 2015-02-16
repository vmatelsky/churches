package com.churches.by.data;

import android.location.Address;

import com.churches.by.data.model.Church;
import com.vla3089.functional.Receiver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DataProvider {

    private static DataProvider instance = new DataProvider();

    public static DataProvider instance() {
        return instance;
    }

    private DataProvider() {

    }

    public void requestChurches(Receiver<List<Church>> churchesList) {
        Locale locale = Locale.US;
        Address address = new Address(locale);
        address.setLatitude(53.5411);
        address.setLongitude(27.3315);
        address.setCountryCode("BY");
        address.setCountryName("Belarus");
        Church church = new Church("St. Smb", address);

        churchesList.receive(Arrays.asList(church));
    }

}
