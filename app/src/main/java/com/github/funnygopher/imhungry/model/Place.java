package com.github.funnygopher.imhungry.model;

import android.support.annotation.NonNull;

public class Place implements Comparable<Place> {

    public Long _id; // Used for Cupboard API
    private String name;
    private String description;
    private int price; // Not a monetary value. More of a scale from 1 - 5.
    private String address;
    private boolean thisSpecificPlace;

    public Place() {
        name = "";
        description = "";
        price = Price.REALLY_CHEAP.getValue();
        address = "";
        thisSpecificPlace = false;
    }

    public Place(String name, String description, int price, String address, boolean thisSpecificPlace) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.address = address;
        this.thisSpecificPlace = thisSpecificPlace;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public boolean isThisSpecificPlace() {
        return thisSpecificPlace;
    }

    @Override
    public int compareTo(@NonNull Place anotherPlace) {
        if(name.equals(anotherPlace.getName())) {
            return 0;
        }

        return -1;
    }
}
