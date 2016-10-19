package com.github.funnygopher.imhungry.injection.components;

import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;
import com.github.funnygopher.imhungry.ui.views.MyPlacesView;

import dagger.Component;

@Component(dependencies = MainComponent.class)
@DaggerScope(MyPlacesComponent.class)
public interface MyPlacesComponent {
    void inject(MyPlacesView view);
}
