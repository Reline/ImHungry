package com.github.funnygopher.imhungry.mortar;

public interface KeyComponentFactory<T> {
    Object createComponent(T parent);
}
