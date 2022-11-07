package  com.appsforkids.pasz.spacelight.Splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.appsforkids.pasz.spacelight.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  ((AnimationDrawable) getWindow().getDecorView().getBackground()).start();

        initianalizationRealm();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initianalizationRealm(){
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