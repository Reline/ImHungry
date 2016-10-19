package com.github.funnygopher.imhungry.mortarflow;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.funnygopher.imhungry.mortar.KeyScoper;

import flow.Services;
import flow.ServicesFactory;
import mortar.MortarScope;

public class DaggerServiceFactory extends ServicesFactory {

    private final Context context;
    private final KeyScoper keyScoper;

    public DaggerServiceFactory(Context context) {
        this.context = context;
        keyScoper = new KeyScoper();
    }

    @Override
    public void bindServices(@NonNull Services.Binder services) {
        MortarScope scope = keyScoper.getKeyScope(context, services.getKey().getClass().getName(), services.getKey());

        if (scope != null) {
            services.bind(services.getKey().getClass().getName(), scope);
        }
    }

    @Override
    public void tearDownServices(@NonNull Services services) {
        super.tearDownServices(services);
        MortarScope scope = keyScoper.getKeyScope(context, services.getKey().getClass().getName(), services.getKey());

        if (scope != null)
            scope.destroy();
    }
}
