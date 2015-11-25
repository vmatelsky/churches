package com.churches.by.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;


public class Church implements Parcelable {

    private final long id;
    private final Bitmap smallIcon;
    private final String name;
    private final Address address;

    public Church(long id, Bitmap smallIcon, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.smallIcon = smallIcon;
    }

    public Church(Parcel in) {
        id = in.readLong();
        smallIcon = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
    }

    public long id() {
        return id;
    }

    public Bitmap smallIcon() {
        return smallIcon;
    }

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public LatLng latLng() {
        return address.location();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Church))return false;

        Church otherChurch = (Church)other;
        return name().equals(otherChurch.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(smallIcon, flags);
        dest.writeString(name);
        dest.writeParcelable(address, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Church> CREATOR = new Parcelable.Creator<Church>() {
        @Override
        public Church createFromParcel(Parcel in) {
            return new Church(in);
        }

        @Override
        public Church[] newArray(int size) {
            return new Church[size];
        }
    };
}
