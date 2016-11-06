package com.github.funnygopher.imhungry.presenters;

import android.support.annotation.NonNull;

import com.github.funnygopher.imhungry.model.Place;
import com.github.funnygopher.imhungry.model.Price;
import com.github.funnygopher.imhungry.model.database.DatabaseAccessObject;
import com.github.funnygopher.imhungry.model.database.RealmAccessObject;
import com.github.funnygopher.imhungry.ui.views.NewPlaceView;

public class NewPlacePresenter extends BasePresenter<DatabaseAccessObject, NewPlaceView> {

    @Override
    public void bindView(@NonNull NewPlaceView view) {
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

    public void createPlace(String name, String desc, int price) {
        model.addPlace(new Place(name, desc, Price.getValue(price), "", false));
    }
}
