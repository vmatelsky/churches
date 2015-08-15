package com.churches.by.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleItem implements Parcelable {

    private final String item;

    public ScheduleItem(String item) {
        this.item = item;
    }

    protected ScheduleItem(Parcel in) {
        item = in.readString();
    }

    public static final Creator<ScheduleItem> CREATOR = new Creator<ScheduleItem>() {
        @Override
        public ScheduleItem createFromParcel(Parcel in) {
            return new ScheduleItem(in);
        }

        @Override
        public ScheduleItem[] newArray(int size) {
            return new ScheduleItem[size];
        }
    };

    public String item() {
        return item;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item);
    }
}
