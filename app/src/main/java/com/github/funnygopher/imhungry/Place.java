package com.github.funnygopher.imhungry;

public class Place implements Comparable<Place> {

    private String name;
    private Price price; // Not a monetary value. More of a scale from 1 - 5.
    private String description;
    private String address;
    private boolean thisSpecificPlace;

    public Place(String name, Price price, String description, String address, boolean thisSpecificPlace) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.address = address;
        this.thisSpecificPlace = thisSpecificPlace;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public boolean isThisSpecificPlace() {
        return thisSpecificPlace;
    }

    @Override
    public int compareTo(Place anotherPlace) {
        if(address.equals(anotherPlace.getAddress())) {
            return 0;
        }

        return -1;
    }

    // A scale for determining the price of a place. Monetary values are not given,
    // because how expensive something is for a user is subject to opinion
    private enum Price {
        CHEAP, KIND_OF_CHEAP, MODERATE, KIND_OF_EXPENSIVE, EXPENSIVE;
    }
}
