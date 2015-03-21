package com.churches.by.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Address implements Parcelable {

    private final String country;
    private final String town;
    private final String street;
    private final LatLng location;

    public Address(String country, String town, String street, LatLng location) {
        this.country = country;
        this.town = town;
        this.street = street;
        this.location = location;
    }

    public Address(Parcel in) {
        country = in.readString();
        town = in.readString();
        street = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
    }

    public String town() {
        return town;
    }

    public String country() {
        return country;
    }

    public String street() {
        return street;
    }

    public LatLng location() {
        return location;
    }

    @Override
    public String toString() {
        return country() + ", " + town() + ", " + street();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(town);
        dest.writeString(street);
        dest.writeParcelable(location, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
