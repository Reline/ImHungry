package com.github.funnygopher.imhungry;

// A scale for determining the price of a place. Monetary values are not given,
// because how expensive something is for a user is subject to opinion
public enum Price {
    REALLY_CHEAP(1), CHEAP(2), WELL_PRICED(3), EXPENSIVE(4), REALLY_EXPENSIVE(5);

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
