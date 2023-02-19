package com.appsforkids.pasz.spacelight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;



@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        //Оновлення мелодій з інтернету
        AddToRealm addToRealm = new AddToRealm(this);
        addToRealm.refreshListFromJSON();

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

    }
}