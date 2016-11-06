package com.github.funnygopher.imhungry.presenters;

import android.support.annotation.NonNull;

import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.database.DatabaseAccessObject;
import com.github.funnygopher.imhungry.model.database.RealmAccessObject;
import com.github.funnygopher.imhungry.ui.views.MyPlacesView;

import io.realm.OrderedRealmCollection;

public class MyPlacesPresenter extends BasePresenter<DatabaseAccessObject, MyPlacesView> {

    @Override
    public void bindView(@NonNull MyPlacesView view) {
        super.bindView(view);
        setModel(new RealmAccessObject());
    }

    @Override
    public void unbindView() {
        super.unbindView();
        model.close();
    }

    @Override
    protected void updateView() {

    }

    public OrderedRealmCollection<Place> getAllPlaces() {
        return (OrderedRealmCollection<Place>) model.getAllPlaces();
    }
}
