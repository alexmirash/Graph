package com.alex.mirash.graph;

import android.app.Application;

/**
 * @author Mirash
 */

public class GraphApp extends Application {
    private static GraphApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static GraphApp getInstance() {
        return instance;
    }
}
