package com.andersen.patterns.creational.singleton;

public class LazyInitSynchronizedSingleton {
    private static LazyInitSynchronizedSingleton INSTANCE = null;

    private LazyInitSynchronizedSingleton() {
    }

    public static LazyInitSynchronizedSingleton getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (LazyInitSynchronizedSingleton.class) {
                if (INSTANCE == null)
                    INSTANCE = new LazyInitSynchronizedSingleton();
            }
        }
        return INSTANCE;
    }
}
