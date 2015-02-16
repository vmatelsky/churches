package com.churches.by.data.model;

import android.location.Address;

import java.io.Serializable;

public class Church implements Serializable {

    private final String name;
    private final Address address;

    public Church(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String name() {
        return name;
    }

    public Address address() {
        return address;
    }
}
