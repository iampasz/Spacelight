package com.appsforkids.pasz.spacelight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.appsforkids.pasz.spacelight.Fragments.MainFragment;
import com.appsforkids.pasz.spacelight.Models.Nightlight;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.io.IOException;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private static final String TAG = "MainActivity";
    private static final String MY_SETTINGS = "my_settings";
    int currentMusicPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScrean();
        setSettings();
        setContentView(R.layout.main);


        if (!isFirstOpen()) {
            //We add nightlights to realm at first time
            addNlToReallm();
        }

        //start MainFragment
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new MainFragment(), "main_fragment").commit();
    }

    private void addNlToReallm() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 400; i <= 600; i++) {
            Nightlight nightlights = realm.createObject(Nightlight.class);
            nightlights.setNumber(i);
            nightlights.setTimer(60000);
        }


        //Перша мелодія яка яку не потрібно завантажувати з інтернету
        AudioFile firstAudio = new AudioFile();
        firstAudio.setResourseLink(R.raw.sound_file_3);
        Log.i("FIRST2", R.raw.sound_file_3 + "    we are");
        Log.i("FIRST2", firstAudio.getResourceLink() + "    we are");

        firstAudio.setNameSong("Stream");
        firstAudio.setAuthorSong("Twarres");
        firstAudio.setStatus(true);
        firstAudio.setLockalLink("plug");

        realm.copyToRealm(firstAudio);

        realm.commitTransaction();
        RealmResults<Nightlight> realmResults = realm.where(Nightlight.class).findAll();


    }

    @SuppressLint("MissingPermission")
    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    //Use this methods from Melody list fragment or when we using save position after close the app

    public void playLockalMusic(String link, Boolean play_status) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            try {
                mediaPlayer.setDataSource(link);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
        } else {

        }


    }
    public void playMusic(int id, Boolean play_status) {

        Log.i("wearehere", "wearehere");
        Log.i("wearehere", id+"wearehere");

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            mediaPlayer = MediaPlayer.create(this, id);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else {

        }
    }

    public void setSettings() {
        int currentAnimationPosition = 0;

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();

        Log.i("settings", settings + "");

        if (settings == null) {
            MySettings mySettings = new MySettings();
            mySettings.setAnimationPosition(0);
            mySettings.setBright(0.5f);
            mySettings.setBackgroundColor(0);
            mySettings.createCoinse(500);
            mySettings.setRate(0);
            mySettings.setAdds(false);
            mySettings.setCurrentMusicPosition(-1);
            mySettings.setTimerTime(-1);

            realm.insert(mySettings);
        }


        AddToRealm addToRealm = new AddToRealm(this);

        //Отримую масив з JSON файла
        // ArrayList<AudioFile> musicArray = addToRealm.getMusicItems();

        // realm.insert(musicArray);

        //  realm.commitTransaction();

        //Отримую максимальний айді доданого останнього файлу. Останній файл і є максимальний
//        if(realm.where(AudioFile.class).findFirst()!=null){
//             maxId =  realm.where(AudioFile.class).max("id");
//        }


        //Порівнюю айдішніки, якщо в файлі джейсон є більший айді за максимально доступний, додаю нову позицію
//       for(int i = 0; i<musicArray.size(); i++){
//
//           if(musicArray.get(i).getId()<=maxId.intValue()){
//
//           }else{
//               realm.insert(musicArray.get(i));
//           }
//        }
//
        realm.commitTransaction();
        addToRealm.getJsonFromURL();
        if (settings == null) {
            Log.i("Hello", "AddToRealm 2" + settings);
            //Запускаем первую анимацию (первый запуск приложения)
            //startAnimation(R.drawable.sm_1);
        } else {
            Log.i("Hello", "No AddToRealm" + settings);
            MyObjects myObjects = new MyObjects(this);
            currentMusicPosition = settings.getCurrentMusicPosition();

            if (currentMusicPosition != -1) {
                Log.i("currentMusicPosition", currentMusicPosition + "currentMusicPosition");

                AudioFile audioFile = getAudios().get(currentMusicPosition);

                if(audioFile.getResourceLink()!=0){
                    playMusic(audioFile.getResourceLink(), true);
                }else{
                    if(audioFile.getLockalLink()!=null){
                        playLockalMusic(audioFile.getLockalLink(),true);
                    }else{
                        playLockalMusic(audioFile.getInternetLink(),true);
                    }
                }

                //playLockalMusic(getAudios().get(currentMusicPosition).getInternetLink(), true);
            }

            if (currentAnimationPosition == -1) {
                //Анимация отключена в настройках
            } else {
                //Запускаем сохраненную анимацию
                // startAnimation(myObjects.getAnimationImage()[currentAnimationPosition]);
            }
        }
    }

    public RealmResults<AudioFile> getAudios() {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<AudioFile> realmResults = realm.where(AudioFile.class).sort("status", Sort.DESCENDING).findAll();
        realm.commitTransaction();

        return realmResults;
    }

    private void setFullScrean() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private Boolean isFirstOpen() {

        Log.i("FIRST", "Is a first open");
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS,
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); // не забудьте подтвердить изменения
        }

        Log.i("FIRST", "Is a first open");

        return hasVisited;
    }



    private void loadBanner() {
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice ("DF666E0D371B0FD388029A01F5323F05").addTestDevice("4642B5D65FCEE3D701A93A8A6ED589EA")
//                .build();
//        AdSize adSize = getAdSize();
//        mAdView.setAdSize(adSize);
//        mAdView.loadAd(adRequest);
    }

    //    public void playMusic(int id, Boolean play_status){
//
//        if(mediaPlayer!=null){
//            mediaPlayer.stop();
//        }
//
//        if(play_status){
//            mediaPlayer = MediaPlayer.create(this, id);
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
//        }else{
//
//        }
//    }
    //    private AdSize getAdSize() {
//        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
//        Display display = getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        display.getMetrics(outMetrics);
//
//        float widthPixels = outMetrics.widthPixels;
//        float density = outMetrics.density;
//
//        int adWidth = (int) (widthPixels / density);
//
//        // Step 3 - Get adaptive ad size and return for setting on the ad view.
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
//    }
}
