package com.github.funnygopher.imhungry.mortar;

import android.content.Context;
import android.support.annotation.Nullable;

import com.github.funnygopher.imhungry.injection.DaggerService;

import mortar.MortarScope;

public class KeyScoper {

    @Nullable
    public MortarScope getKeyScope(Context context, String name, Object key) {
        MortarScope parentScope = MortarScope.getScope(context);

        MortarScope childScope = parentScope.findChild(name);
        if (childScope != null) {
            return childScope;
        }

        if (!(key instanceof KeyComponentFactory)) {
            return null;
        }
        KeyComponentFactory keyComponentFactory = (KeyComponentFactory) key;
        Object component = keyComponentFactory.createComponent(parentScope.getService(DaggerService.SERVICE_NAME));

        MortarScope.Builder builder = parentScope.buildChild().withService(DaggerService.SERVICE_NAME, component);

        return builder.build(name);
    }
}