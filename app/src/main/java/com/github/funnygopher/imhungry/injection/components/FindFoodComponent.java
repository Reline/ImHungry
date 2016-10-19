package com.github.funnygopher.imhungry.injection.components;

import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;
import com.github.funnygopher.imhungry.ui.views.FindFoodView;

import dagger.Component;

@Component(dependencies = MainComponent.class)
@DaggerScope(FindFoodComponent.class)
public interface FindFoodComponent {
    void inject(FindFoodView view);
}
