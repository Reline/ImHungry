package com.github.funnygopher.imhungry.flow.keys;

import com.github.funnygopher.imhungry.R;
import com.github.funnygopher.imhungry.flow.Layout;
import com.github.funnygopher.imhungry.injection.components.DaggerFindFoodComponent;
import com.github.funnygopher.imhungry.injection.components.MainComponent;
import com.github.funnygopher.imhungry.mortar.KeyComponentFactory;

import flow.ClassKey;

@Layout(R.layout.find_food_view)
public class FindFoodKey extends ClassKey implements KeyComponentFactory<MainComponent> {

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof FindFoodKey;
    }

    @Override
    public Object createComponent(MainComponent parent) {
        return DaggerFindFoodComponent
                .builder()
                .mainComponent(parent)
                .build();
    }
}
