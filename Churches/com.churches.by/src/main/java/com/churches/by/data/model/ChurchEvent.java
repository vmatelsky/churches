package com.churches.by.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ChurchEvent implements Parcelable {

    private final Date startDate;
    private final String title;

    public ChurchEvent(Date startDate, String title) {
        this.startDate = startDate;
        this.title = title;
    }

    public ChurchEvent(Parcel in) {
        startDate = (Date) in.readSerializable();
        title = in.readString();
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChurchEvent> CREATOR = new Parcelable.Creator<ChurchEvent>() {
        @Override
        public ChurchEvent createFromParcel(Parcel in) {
            return new ChurchEvent(in);
        }

        @Override
        public ChurchEvent[] newArray(int size) {
            return new ChurchEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(startDate);
        dest.writeString(title);
    }
}
