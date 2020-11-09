package com.andersen.patterns.creational.singleton;

import java.util.Objects;

public class LazyInitSynchronizedSingleton {
    private static LazyInitSynchronizedSingleton INSTANCE = null;

    private LazyInitSynchronizedSingleton() {
    }

    public static LazyInitSynchronizedSingleton getINSTANCE() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (LazyInitSynchronizedSingleton.class) {
                if (Objects.isNull(INSTANCE))
                    INSTANCE = new LazyInitSynchronizedSingleton();
            }
        }
        return INSTANCE;
    }
}
