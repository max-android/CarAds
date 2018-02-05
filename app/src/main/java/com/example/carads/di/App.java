package com.example.carads.di;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


public class App extends Application {


    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .serviceModule(new ServiceModule())
                .databaseModule(new DatabaseModule(this))
                .glideModule(new GlideModule(this))
                .myFavoritesModule(new MyFavoritesModule(this))
                .permissionModule(new PermissionModule())
                        .fileModule(new FileModule(this))
                .firebaseModule(new FirebaseModule())
                .build();
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

}
