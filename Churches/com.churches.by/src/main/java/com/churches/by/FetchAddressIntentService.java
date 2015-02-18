package com.churches.by;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.IOException;
import java.util.List;

public class FetchAddressIntentService extends IntentService {

    public static final String PACKAGE_NAME = "com.churches.by";
    public static final String REQUEST_ADDRESS = PACKAGE_NAME + ".requested_address";

    public static final String FETCHED_ADDRESS = PACKAGE_NAME + ".fetched_address";

    public static final String RESULT_RECEIVER = "result_receiver";
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;

    private static final int MAX_FETCHED_ADDRESSES_COUNT = 1;

    public FetchAddressIntentService() {
        super(FetchAddressIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int resultCode = SUCCESS_RESULT;
        Bundle resultData = new Bundle();

        try {
            Address fetchedAddress = getAddress(requestedAddressFromIntent(intent));
            resultData.putParcelable(FETCHED_ADDRESS, fetchedAddress);
        } catch (IOException e) {
            e.printStackTrace();
            resultCode = FAILURE_RESULT;
        }

        ResultReceiver resultReceiver = receiverFromIntent(intent);
        if (resultReceiver != null) {
            resultReceiver.send(resultCode, resultData);
        }
    }

    private Geocoder createGeocoder() {
        return new Geocoder(this, LocaleManager.defaultLocale());
    }

    private String requestedAddressFromIntent(Intent intent) {
        return intent.getStringExtra(REQUEST_ADDRESS);
    }

    private ResultReceiver receiverFromIntent(Intent intent) {
        return intent.getParcelableExtra(RESULT_RECEIVER);
    }

    private Address getAddress(String addressString) throws IOException {
        Geocoder geocoder = createGeocoder();
        List<Address> addressList = geocoder.getFromLocationName(addressString, MAX_FETCHED_ADDRESSES_COUNT);
        return addressList.get(0);
    }

}
