package com.github.funnygopher.imhungry.injection.components;

import com.github.funnygopher.imhungry.ImHungry;
import com.github.funnygopher.imhungry.injection.modules.AppModule;
import com.github.funnygopher.imhungry.injection.scopes.DaggerScope;

import dagger.Component;

@Component(modules = {AppModule.class})
@DaggerScope(AppComponent.class)
public interface AppComponent {
    void inject(ImHungry app);
}
