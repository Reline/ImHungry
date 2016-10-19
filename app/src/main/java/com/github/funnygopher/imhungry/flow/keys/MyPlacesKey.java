package com.github.funnygopher.imhungry.flow.keys;

import com.github.funnygopher.imhungry.injection.components.DaggerMyPlacesComponent;
import com.github.funnygopher.imhungry.injection.components.MainComponent;
import com.github.funnygopher.imhungry.mortar.KeyComponentFactory;

import flow.ClassKey;

public class MyPlacesKey extends ClassKey implements KeyComponentFactory<MainComponent> {

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof MyPlacesKey;
    }

    @Override
    public Object createComponent(MainComponent parent) {
        return DaggerMyPlacesComponent
                .builder()
                .mainComponent(parent)
                .build();
    }
}
