package com.churches.by.data.dummy;

import android.graphics.BitmapFactory;

import com.churches.by.CAppliation;
import com.churches.by.R;
import com.churches.by.data.model.Address;
import com.churches.by.data.model.Church;
import com.google.android.gms.maps.model.LatLng;

public class DummyChurch {

    public static Church Borisov = new Church(
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov1),
            "Касцёл Нараджэння Найсвяцейшай Панны Марыі",
            new Address("Беларусь", "Барысаў", "ул. 3-ега Інтэрнацыянала, 28", new LatLng(54.241771, 28.505767)));

    public static Church Katedra = new Church(
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.minsk_katedra),
            "Архікатэдра Імя Найсвяцейшай Панны Марыі",
            new Address("Беларусь", "Мінск", "пл. Свабоды, 9", new LatLng(53.9031514, 27.5546604)));
}
