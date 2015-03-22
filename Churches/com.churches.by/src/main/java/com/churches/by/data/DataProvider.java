package com.churches.by.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.churches.by.CAppliation;
import com.churches.by.R;
import com.churches.by.data.model.Address;
import com.churches.by.data.model.Church;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.data.model.ChurchEvent;
import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.Date;
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

        Address address = new Address("Беларусь", "Барысаў", "ул. 3-ега Інтэрнацыянала, 28", new LatLng(54.241771, 28.505767));
        Bitmap image = BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov1);
        Church church = new Church(image, "Касцёл Нараджэння Найсвяцейшай Панны Марыі", address);

        Address address1 = new Address("Беларусь", "Мінск", "пл. Свабоды, 9", new LatLng(53.9031514, 27.5546604));
        Bitmap image1 = BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.minsk_katedra);
        Church church1 = new Church(image1, "Архікатэдра Імя Найсвяцейшай Панны Марыі", address1);

        return Arrays.asList(
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1,
                church,
                church1);
    }

    public List<Church> favoritedChurches() {
        return churches();
    }

    public Observable.OnSubscribe<ChurchDetails> churchDetails(final Church church) {
        return new Observable.OnSubscribe<ChurchDetails>() {
            @Override
            public void call(Subscriber<? super ChurchDetails> subscriber) {
                ChurchEvent event = new ChurchEvent(new Date(), "Test event");

                ChurchDetails details = new ChurchDetails.Builder()
                        .church(church)
                        .image(BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov2))
                        .events(Arrays.asList(event, event, event, event, event, event, event, event, event))
                        .build();
                subscriber.onNext(details);
                subscriber.onCompleted();
            }
        };
    }
}
