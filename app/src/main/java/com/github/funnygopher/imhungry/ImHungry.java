package com.github.funnygopher.imhungry;

import android.app.Application;

import com.github.funnygopher.imhungry.injection.DaggerService;
import com.github.funnygopher.imhungry.injection.components.AppComponent;
import com.github.funnygopher.imhungry.injection.components.DaggerAppComponent;
import com.github.funnygopher.imhungry.injection.modules.AppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mortar.MortarScope;
import timber.log.Timber;

public class ImHungry extends Application {

    private MortarScope mortarScope;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        if (mortarScope == null) {
            setupMortarScope();
        }
    }

    private void setupMortarScope() {
        AppComponent component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);

        mortarScope = MortarScope.buildRootScope()
                .withService(DaggerService.SERVICE_NAME, component)
                .build("Root");
    }

    @Override
    public Object getSystemService(String name) {
        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }
}
