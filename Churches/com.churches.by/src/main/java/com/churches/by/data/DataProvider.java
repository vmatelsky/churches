package com.churches.by.data;

import com.churches.by.data.dummy.DummyChurch;
import com.churches.by.data.dummy.DummyChurchDetails;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    public Observable<List<Church>> churchesAsync() {
        return createChurchesPerformer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Church> churchById(final long id) {
        return churchesAsync()
                .flatMapIterable(chs -> chs)
                .filter(church -> church.id() == id)
                .first();
    }

    private Observable<List<Church>> createChurchesPerformer() {
        return Observable.just(churches());
    }

    public Observable<ChurchDetails> churchDetails(final Church church) {
        ChurchDetails details = DummyChurchDetails.Borisov;

        if (church.equals(DummyChurch.Katedra)) {
            details = DummyChurchDetails.Katedra;
        }

        return Observable.just(details);
    }

    public Observable<ChurchDetails> churchDetailsByChurchId(final long churchId) {
        ChurchDetails details = DummyChurchDetails.Borisov;

        if (churchId == DummyChurch.Katedra.id()) {
            details = DummyChurchDetails.Katedra;
        }

        return Observable.just(details);
    }

}
