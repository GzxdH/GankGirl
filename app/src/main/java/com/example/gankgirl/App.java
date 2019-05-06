package com.example.gankgirl;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;
    public static App instances;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instances = this;
    }

    public static Context getContext() {
        return context;
    }

    public static App getInstances() {
        return instances;
    }
}
