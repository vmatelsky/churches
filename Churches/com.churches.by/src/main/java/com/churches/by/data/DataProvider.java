package com.churches.by.data;

import com.churches.by.data.dummy.DummyChurch;
import com.churches.by.data.dummy.DummyChurchDetails;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.data.model.receivers.ChurchesReceiver;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void churchesAsync(ChurchesReceiver churchesReceiver) {
//        Observable.create(createChurchesPerformer())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(churchesReceiver);
    }

    private Observable.OnSubscribe<List<Church>> createChurchesPerformer() {
        return new Observable.OnSubscribe<List<Church>>() {
            @Override
            public void call(Subscriber<? super List<Church>> subscriber) {
                subscriber.onNext(churches());
                subscriber.onCompleted();
            }
        };
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

    public void favoritedChurchesAsync(ChurchesReceiver churchesReceiver) {
        churchesAsync(churchesReceiver);
    }
}
