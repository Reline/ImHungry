package com.github.funnygopher.imhungry.presenters;

import android.os.Bundle;

import com.github.funnygopher.imhungry.injection.components.MyPlacesComponent;
import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.database.RealmService;
import com.github.funnygopher.imhungry.ui.views.MyPlacesView;

import javax.inject.Inject;

import io.realm.OrderedRealmCollection;
import mortar.ViewPresenter;

@DaggerScope(MyPlacesComponent.class)
public class MyPlacesPresenter extends ViewPresenter<MyPlacesView> {

    private RealmService realmService;

    @Inject
    public MyPlacesPresenter() {
        // default constructor
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        realmService = new RealmService();
    }

    public OrderedRealmCollection<Place> getData() {
        return realmService.getAllPlaces();
    }

    @Override
    public void dropView(MyPlacesView view) {
        realmService.close();
        super.dropView(view);
    }
}
