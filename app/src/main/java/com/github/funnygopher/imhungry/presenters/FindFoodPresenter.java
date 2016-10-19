package com.github.funnygopher.imhungry.presenters;

import android.os.Bundle;

import com.github.funnygopher.imhungry.injection.components.FindFoodComponent;
import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;
import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.database.RealmService;
import com.github.funnygopher.imhungry.ui.views.FindFoodView;

import javax.inject.Inject;

import mortar.ViewPresenter;

@DaggerScope(FindFoodComponent.class)
public class FindFoodPresenter extends ViewPresenter<FindFoodView> {

    RealmService realmService;

    @Inject
    public FindFoodPresenter() {
        // default constructor
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
        realmService = new RealmService();
    }

    public Place getRandomPlace(int value) {
        return realmService.getRandomPlace(value);
    }

    @Override
    public void dropView(FindFoodView view) {
        realmService.closeRealm();
        super.dropView(view);
    }
}
