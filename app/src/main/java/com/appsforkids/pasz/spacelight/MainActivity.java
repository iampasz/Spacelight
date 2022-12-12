package com.appsforkids.pasz.spacelight;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.appsforkids.pasz.spacelight.Fragments.MainFragment;
import com.appsforkids.pasz.spacelight.Fragments.MelodyListFragment;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_AUDIO = "my_audio";
    String savedAudioFile = "";
    ArrayList <AudioFile> arrayList;

    String activeAudio = "";
    String previousAudio = "";

    Boolean isLooping = false;

    public ConstraintLayout constrain;


    SoundPool soundPool;


    @BindView(R.id.right_p)
    ImageView right_p;

    @BindView(R.id.show_menu)
    ImageView show_menu;

    @BindView(R.id.name_song)
    TextView name_song;

    @BindView(R.id.player)
    LinearLayout player;

    @BindView(R.id.play_p)
    ImageView play_p;

    @BindView(R.id.melody_list)
    ImageView melody_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.bind(this);


         arrayList = getAudios();


        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundPool.load(this, R.raw.s1, 1);
        soundPool.load(this, R.raw.s2, 1);
        soundPool.load(this, R.raw.s3, 1);
        soundPool.load(this, R.raw.s4, 1);
        soundPool.load(this, R.raw.s5, 1);

        constrain = (ConstraintLayout) findViewById(R.id.constrain);

        //Встановлюємо повно-екранний режим
        setFullScrean();

        //Встановлюємо стандартні налаштунки
        if (!isFirstOpen()) {
            assert Realm.getDefaultConfiguration() != null;
            Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
            realm.beginTransaction();
            realm.insert(getDefualtSettings());
            realm.commitTransaction();
        }



        //Якщо після виходу з додатку грала мелодія, вона буде грати при заходженні в додаток повторно
         playSavedAudio();

        //Відкриваємо MainFragment

        MainFragment mainFragment = new MainFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, mainFragment, "main_fragment").commit();


        right_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_song.setText(playNextAudio());
                play_p.setImageResource(R.drawable.bt_pause);

            }
        });

        show_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFragment.rv.setVisibility(View.VISIBLE);
                mainFragment.rv.animate().alpha(1f).setDuration(1000);
                hidePlayer();
            }
        });

        play_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity)getActivity()).startStop();
                startStop();
            }
        });

        melody_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, new MelodyListFragment(), "MelodyListFragment").commit();
            }
        });


    }

    //Встановлюємо повно-екранний режим
    private void setFullScrean() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //Перевірка на перше відкриття
    private Boolean isFirstOpen() {
        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); // не забудьте подтвердить изменения
        }

        return hasVisited;
    }

    //Отримання стандартних налаштунків
    public MySettings getDefualtSettings() {

        MySettings mySettings = new MySettings();
        mySettings.setAnimationPosition(0);
        mySettings.setBright(0.5f);
        mySettings.setBackgroundColor(0);
        mySettings.createCoinse(500);
        mySettings.setRate(0);
        mySettings.setAdds(false);
        mySettings.setCurrentMusic("");
        mySettings.setTimerTime(-1);

        return mySettings;
    }

    //Програвання музики
    public void playLockalMusic(AudioFile audioFile, Boolean play_status) {

        savedAudioFile = "";

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(isLooping);
            try {
                mediaPlayer.setDataSource(audioFile.getInternetLink());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();

            savedAudioFile = audioFile.getInternetLink();

            play_p.setImageResource(R.drawable.bt_pause);
            name_song.setText(audioFile.nameSong);

        }else{
            play_p.setImageResource(R.drawable.bt_play);
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playNextAudio();
                //Toast.makeText(MainActivity.this, "deded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void playLockalMusic(String link, Boolean play_status) {

        savedAudioFile = "";

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(isLooping);
            try {
                mediaPlayer.setDataSource(link);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();

            savedAudioFile = link;

            play_p.setImageResource(R.drawable.bt_pause);

            name_song.setText(link);

        }else{
            play_p.setImageResource(R.drawable.bt_play);
        }
        activeAudio = link;

        //Toast.makeText(MainActivity.this, "here", Toast.LENGTH_SHORT).show();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playNextAudio();
                //Toast.makeText(MainActivity.this, "deded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void playInternetMusic(AudioFile audioFile, Boolean play_status) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            play_p.setImageResource(R.drawable.bt_pause);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(isLooping);
            try {
                mediaPlayer.setDataSource(audioFile.getInternetLink());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            name_song.setText(audioFile.nameSong);
        }else{
            play_p.setImageResource(R.drawable.bt_play);
        }
    }

    public void playMusic(int id, Boolean play_status) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (play_status) {
            mediaPlayer = MediaPlayer.create(this, id);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(isLooping);
            mediaPlayer.start();
        }else{
            play_p.setImageResource(R.drawable.bt_play);
        }
    }
    private void playSavedAudio() {
        Log.i("playSavedAudio", "playSavedAudio");
        SharedPreferences spa = getSharedPreferences(MY_AUDIO, Context.MODE_PRIVATE);
        String savedAudio = spa.getString("audioFile", "");

        if (savedAudio == "") {
            Log.i("playSavedAudio", "empty");
        } else {
            playLockalMusic(savedAudio, true);
            Log.i("playSavedAudio", savedAudio+" savedAudio");
            refreshSavingAudio(savedAudio);
        }
    }

    private void refreshSavingAudio(String savedAudio) {

        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        AudioFile audioFile = realm.where(AudioFile.class).equalTo("lockalLink", savedAudio).findFirst();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setCurrentMusic(audioFile.getNameSong());
        Log.i("playSavedAudio", savedAudio+" refreshSavingAudio");
        realm.commitTransaction();
    }

    //Збереження мелодії після виходу з додатку
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveAudio();
    }
    private void saveAudio() {
        SharedPreferences spa = getSharedPreferences(MY_AUDIO, Context.MODE_PRIVATE);

        if (savedAudioFile != "") {
            // выводим нужную активность
            SharedPreferences.Editor e = spa.edit();
            e.putString("audioFile", savedAudioFile);
            e.commit(); // не забудьте подтвердить изменения
        }
    }



    public void platSPool(int position){
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = curVolume / maxVolume;
        float rightVolume = curVolume / maxVolume;
        int priority = 1;
        int no_loop = 0;
        float normal_playback_rate = 1f;
        soundPool.play(position, leftVolume, rightVolume, priority, no_loop,
                normal_playback_rate);

    }

    private ArrayList<AudioFile> getAudios(){


        ArrayList<AudioFile> arrayList = new ArrayList<>();

        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        RealmResults<AudioFile> realmResults;
        //RealmResults<AudioFile> realmResults = realm.where(AudioFile.class).findAll();

        //Перша мелодія яка яку не потрібно завантажувати з інтернету
        AudioFile firstAudio = new AudioFile();
        firstAudio.setResourseLink(R.raw.sound_file_3);
        firstAudio.setNameSong("Stream");
        firstAudio.setAuthorSong("Twarres");
        firstAudio.setStatus(true);
        firstAudio.setLockalLink("plug");

        arrayList.add(firstAudio);
        realmResults = realm.where(AudioFile.class).equalTo("status", true).findAll();
        arrayList.addAll(realmResults);

        return arrayList;
    }

    public String playNextAudio(){

        int allAudio = arrayList.size();
        Random random = new Random();
        int randomAudioNumber = random.nextInt(allAudio);
        previousAudio = activeAudio;

        Log.i("next", allAudio+" allAudio");
        Log.i("next", randomAudioNumber+" randomAudioNumber");
        Log.i("next", previousAudio+" previousAudio");

        if(allAudio>2){
            if(arrayList.get(randomAudioNumber).getLockalLink().equals(activeAudio) || arrayList.get(randomAudioNumber).getLockalLink().equals(previousAudio) ){
                playNextAudio();
            }else{
                playLockalMusic(arrayList.get(randomAudioNumber).getLockalLink(), true);
                return arrayList.get(randomAudioNumber).getNameSong();
            }
        }

        return "";
    }

    public Boolean startStop(){
        if(mediaPlayer!=null){

            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                play_p.setImageResource(R.drawable.bt_play);
                Log.i("isplay", " true");
                return false;


            }else{
                Log.i("isplay", " false");
                if(mediaPlayer!=null){
                    mediaPlayer.start();
                    play_p.setImageResource(R.drawable.bt_pause);
                    Log.i("isplay", mediaPlayer+" false");
                    return true;
                }
            }

        }else{
            playNextAudio();
        }


        return false;
    }

    public void playLoopingAudio(Boolean status){
        isLooping = status;
    }

    public void hidePlayer(){
        player.animate().translationY(200).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                player.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    public void showPlayer(){
        player.setVisibility(View.VISIBLE);
        player.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                player.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

}
