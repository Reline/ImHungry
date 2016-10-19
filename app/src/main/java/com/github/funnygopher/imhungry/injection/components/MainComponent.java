package com.github.funnygopher.imhungry.injection.components;

import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;
import com.github.funnygopher.imhungry.ui.activities.MainActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@DaggerScope(MainComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
