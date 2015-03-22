package com.churches.by.data;

import com.churches.by.data.dummy.DummyChurch;
import com.churches.by.data.dummy.DummyChurchDetails;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class DataProvider {

    private static DataProvider instance = new DataProvider();

    public static DataProvider instance() {
        return instance;
    }

    private DataProvider() {

    }

    public List<Church> churches() {
        return Arrays.asList(
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra,
                DummyChurch.Borisov,
                DummyChurch.Katedra);
    }

    public List<Church> favoritedChurches() {
        return churches();
    }

    public Observable.OnSubscribe<ChurchDetails> churchDetails(final Church church) {
        return new Observable.OnSubscribe<ChurchDetails>() {
            @Override
            public void call(Subscriber<? super ChurchDetails> subscriber) {
                ChurchDetails details = DummyChurchDetails.Borisov;

                if (church.equals(DummyChurch.Katedra)) {
                    details = DummyChurchDetails.Katedra;
                }
                subscriber.onNext(details);
                subscriber.onCompleted();
            }
        };
    }
}
