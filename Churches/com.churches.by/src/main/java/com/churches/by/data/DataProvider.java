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

    public List<Church> churches() {
        Locale locale = Locale.US;
        Address address = new Address(locale);
        address.setLatitude(53.9031514);
        address.setLongitude(27.5546604);
        address.setCountryCode("BY");
        address.setCountryName("Belarus");
        Church church = new Church("St. Smb", address);

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

    public void requestChurches(Receiver<List<Church>> churchesList) {
        churchesList.receive(churches());
    }

}
