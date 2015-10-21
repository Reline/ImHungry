package com.github.funnygopher.imhungry;

public class Place implements Comparable<Place> {

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

    // A scale for determining the price of a place. Monetary values are not given,
    // because how expensive something is for a user is subject to opinion
    public enum Price {
        CHEAP(1), KIND_OF_CHEAP(2), MODERATE(3), KIND_OF_EXPENSIVE(4), EXPENSIVE(5);

        private int value;

        Price(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getMonetaryString() {
            StringBuilder price = new StringBuilder();
            for(int i = 0; i < value; i++) {
                price.append("$");
            }
            return price.toString();
        }

        @Override
        public String toString() {
            String name = name().toLowerCase();
            StringBuilder nameBuilder = new StringBuilder();
            boolean space = false;
            for(int i = 0; i < name.length(); i++) {
                if(i == 0 || space) {
                    nameBuilder.append(name.substring(i, i + 1).toUpperCase());
                    space = false;
                }else if(name.charAt(i) == '_') {
                    nameBuilder.append(" ");
                    space = true;
                } else {
                    nameBuilder.append(name.charAt(i));
                }
            }

            return nameBuilder.toString();
        }
    }
}
