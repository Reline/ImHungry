package com.github.funnygopher.imhungry;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Comparable<Place>, Parcelable {

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

    public Place(Parcel parcel) {
        boolean hasId = parcel.readByte() != 0;
        if(hasId) {
            _id = parcel.readLong();
        }
        name = parcel.readString();
        description = parcel.readString();
        price = parcel.readInt();
        address = parcel.readString();
        thisSpecificPlace = parcel.readByte() != 0;
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
    public int compareTo(Place anotherPlace) {
        if(name.equals(anotherPlace.getName())) {
            return 0;
        }

        return -1;
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {

        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(price);
        dest.writeString(address);
        dest.writeByte((byte) (thisSpecificPlace ? 1 : 0));
    }
}
