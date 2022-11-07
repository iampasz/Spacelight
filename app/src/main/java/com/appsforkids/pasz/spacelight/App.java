package com.appsforkids.pasz.spacelight;

import android.app.Application;
import android.view.WindowManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //ініціалізація Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().
                name("realmName")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }


}
