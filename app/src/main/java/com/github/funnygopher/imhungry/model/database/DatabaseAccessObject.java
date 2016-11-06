package com.github.funnygopher.imhungry.model.database;

import com.github.funnygopher.imhungry.model.Place;

import java.io.Closeable;
import java.util.Collection;

public interface DatabaseAccessObject extends Closeable {
    void addPlace(Place place);
    Place getRandomPlace(int value);
    Collection<Place> getAllPlaces();

    @Override
    void close();
}
