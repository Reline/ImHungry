package com.github.funnygopher.imhungry;

public class Place implements Comparable<Place> {

    public Long _id; // Used for Cupboard API
    private String name;
    private String description;
    private Price price; // Not a monetary value. More of a scale from 1 - 5.
    private String address;
    private boolean thisSpecificPlace;

    public Place(String name, String description, Price price, String address, boolean thisSpecificPlace) {
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

    public Price getPrice() {
        return price;
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
}
