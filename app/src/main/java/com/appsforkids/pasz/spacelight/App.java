package com.appsforkids.pasz.spacelight;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends MultiDexApplication {

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
