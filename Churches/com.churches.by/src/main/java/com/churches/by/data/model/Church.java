package com.churches.by.data.model;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Church implements Parcelable {

    private final String name;
    private final Address address;

    public Church(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Church(Parcel in) {
        name = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
    }

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public LatLng latLng() {
        return new LatLng(address.getLatitude(), address.getLongitude());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
