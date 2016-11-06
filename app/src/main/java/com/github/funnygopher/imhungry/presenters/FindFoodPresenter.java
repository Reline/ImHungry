package com.github.funnygopher.imhungry.presenters;

import android.support.annotation.NonNull;

import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.database.DatabaseAccessObject;
import com.github.funnygopher.imhungry.model.database.RealmAccessObject;
import com.github.funnygopher.imhungry.ui.views.FindFoodView;

public class FindFoodPresenter extends BasePresenter<DatabaseAccessObject, FindFoodView> {

    @Override
    public void bindView(@NonNull FindFoodView view) {
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

    public Place getRandomPlace(int value) {
        return model.getRandomPlace(value);
    }
}
