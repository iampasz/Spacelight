package com.appsforkids.pasz.spacelight;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SubtitleData;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Adapters.RecyclerViewAdapter;
import com.appsforkids.pasz.spacelight.Fragments.AudioListFragment;
import com.appsforkids.pasz.spacelight.Fragments.MainFragment;
import com.appsforkids.pasz.spacelight.Fragments.MelodyListFragment;
import com.appsforkids.pasz.spacelight.Fragments.MessageFragment;
import com.appsforkids.pasz.spacelight.Fragments.SimpleMessageFragment;
import com.appsforkids.pasz.spacelight.Interfaces.ChangeColors;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnErrorListener {

    MediaPlayer mediaPlayer;
    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_AUDIO = "my_audio";
    String savedAudioFile = "";
    ArrayList <AudioFile> arrayList;
    //int activeAudio;
    //int previousAudio;
    Boolean isLooping = false;
    SoundPool soundPool;
    private AdView mAdView;
    AdRequest adRequest;

    Boolean loopingMelody = false;

    public LinearLayout main_m;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.home_b)
    ImageView home_b;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.right_p)
    ImageView right_p;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.random_list)
    ImageView random_list;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.player)
    LinearLayout player;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.play_p)
    ImageView play_p;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.constrain)
    public ConstraintLayout constrain;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.melody_list)
    ImageView melody_list;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.audio_name)
    public TextView audio_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv)
    public RecyclerView rv;

    RecyclerViewAdapter adapter;

    MainFragment mainFragment;

    MyObjects myObjects;

    String currentLink;
    String currentName;

    int audioCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.bind(this);

        float dip = 80f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );




//        main_m.setY(px);

        home_b.setOnClickListener(this);
        right_p.setOnClickListener(this);
        play_p.setOnClickListener(this);
        melody_list.setOnClickListener(this);
        random_list.setOnClickListener(this);

        audio_name.setSelected(true);

        rv.setY(px);
        arrayList = getAudios();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//        soundPool.load(this, R.raw.s1, 1);
//        soundPool.load(this, R.raw.s2, 1);
//        soundPool.load(this, R.raw.s3, 1);
//        soundPool.load(this, R.raw.s4, 1);
//        soundPool.load(this, R.raw.s5, 1);

        //Встановлюємо повно-екранний режим
        setFullScrean();

        myObjects = new MyObjects(this);

        //Встановлюємо стандартні налаштунки
        if (!isFirstOpen()) {
            assert Realm.getDefaultConfiguration() != null;
            Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
            realm.beginTransaction();
            realm.insert(getDefualtSettings());
            realm.commitTransaction();
        }


        audio_name.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        //Реклама
        //mAdView = (AdView) findViewById(R.id.adView);

        mAdView = findViewById(R.id.adView);

        setMyAds();
        adRequest = new AdRequest.Builder().build();

        //Якщо після виходу з додатку грала мелодія, вона буде грати при заходженні в додаток повторно
        //playSavedAudio();

        //Відкриваємо MainFragment

         mainFragment = new MainFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, mainFragment, "main_fragment").commit();

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(llm);

        //Кнопки меню
        adapter = new RecyclerViewAdapter(myObjects.getMenuButtons());
        adapter.MyOnclick(new ChangeColors() {
            @Override
            public void onclick(int button) {
                switch (button) {
                    case 0:
                        //back button
                        hideRvMenu();
                        showPlayer();
                        break;
                    case 1:
                        //bg colors
                        mainFragment.changeBgColor();
                        break;
                    case 2:
                        //bg images
                        mainFragment.changeBackgroundImage();

                        break;
                    case 3:
                        //timer
                        mainFragment.setTimer();
                        break;
                    case 4:
                        //animation
                        mainFragment.startAnim();

                        break;
                    case 5:
                        //bright
                        mainFragment.changeBrighest();
                        break;
                    case 6:
                        //suit color
                        mainFragment.changeSuitColor();
                        break;
                    case 7:
                        //politic
                        mainFragment.openPrivatePolicy();
                        break;
                    case 8:
                        break;
                }
            }
        });
        rv.setAdapter(adapter);
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
            e.apply(); // не забудьте подтвердить изменения
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
            mediaPlayer.reset();
        }

        Log.i("press_play", mediaPlayer + " we are press play");

    }

    public void playLockalMusic(String link, String name,  Boolean play_status) {

        currentLink = link;
        currentName = name;

        savedAudioFile = "";
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        if (play_status) {
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setOnErrorListener(this);

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

            play_p.setImageResource(R.drawable.pause_vector_gradient);

           // audio_name.setText(name);
            audio_name.setText(name);

        }else{
            play_p.setImageResource(R.drawable.play_vector_gradient);
        }
        //activeAudio = link;

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
               // mediaPlayer.release();
                playNextAudio();
                //Toast.makeText(MainActivity.this, "deded", Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("statusp", mediaPlayer.isLooping()+" st");

    }

    public void playInternetMusic(AudioFile audioFile, Boolean play_status) {

        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }

        if (play_status) {
            play_p.setImageResource(R.drawable.play_vector_gradient);
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
            //name_song.setText(audioFile.nameSong);
        }else{
            play_p.setImageResource(R.drawable.play_vector_gradient);
        }
    }

    @SuppressLint("SetTextI18n")
    public void playMusic(int id, String name, String auth, Boolean play_status) {

        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }

        if (play_status) {
            mediaPlayer = MediaPlayer.create(this, id);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(isLooping);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //mediaPlayer.release();
                playNextAudio();
                //Toast.makeText(MainActivity.this, "deded", Toast.LENGTH_SHORT).show();
            }
        });

            mediaPlayer.start();
            audio_name.setText(name+", "+auth);

        }else{
            play_p.setImageResource(R.drawable.play_vector_gradient);
        }
    }

    private void playSavedAudio() {
        Log.i("playSavedAudio", "playSavedAudio");
        SharedPreferences spa = getSharedPreferences(MY_AUDIO, Context.MODE_PRIVATE);
        String savedAudio = spa.getString("audioFile", "");

        if (savedAudio == "") {
            Log.i("playSavedAudio", "empty");
        } else {
            playLockalMusic(savedAudio, "", true);
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
            e.apply(); // не забудьте подтвердить изменения
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
        getAudios();

        switch (allAudio){
            case 0:
                //Have not any audio
                break;
            case 1:
                //Have first audio with id
                        if(arrayList.get(audioCounter).getResourceLink()!=0){
                            playMusic(arrayList.get(audioCounter).getResourceLink(), arrayList.get(audioCounter).nameSong, arrayList.get(audioCounter).authorSong, true);
                            //activeAudio=audioCounter;
                        }
                break;

            default:
                //Have melody with lockal link

                if(arrayList.get(audioCounter).getResourceLink()!=0){
                    playMusic(arrayList.get(audioCounter).getResourceLink(), arrayList.get(audioCounter).nameSong, arrayList.get(audioCounter).authorSong, true);
                   // activeAudio=audioCounter;
                }else{
                    playLockalMusic(
                            arrayList.get(audioCounter).getLockalLink(),
                            arrayList.get(audioCounter).getNameSong()+", "+arrayList.get(audioCounter).authorSong,
                            true);
                    //activeAudio=audioCounter;
                }
                break;
        }

        audioCounter++;

        Log.i("audioCounter",audioCounter+" rrr "+allAudio);

        if(audioCounter==allAudio){
            audioCounter=0;
        }

        return "";
    }

    public Boolean startStop(){

        Log.i("isplay", " startStop");

        if(mediaPlayer!=null){

            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                play_p.setImageResource(R.drawable.play_vector_gradient);
                Log.i("isplay", " true");
                return false;

            }else{
                Log.i("isplay", " false");
                if(mediaPlayer!=null){
                    mediaPlayer.start();
                    play_p.setImageResource(R.drawable.pause_vector_gradient);
                    Log.i("isplay", mediaPlayer+" false");
                    return true;
                }
            }

        }else{
            play_p.setImageResource(R.drawable.pause_vector_gradient);
            playNextAudio();
            Log.i("isplay", " pause_vector_gradient");
        }


        return false;
    }

    public void playLoopingAudio(Boolean status){
        isLooping = status;
    }

    public void hidePlayer(){
        player.animate().translationY(player.getHeight()).setDuration(1000);

    }

    public void showPlayer(){
        player.animate().translationY(0).setDuration(1000);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_b:
                rv.animate().translationY(0).setDuration(1000);
                hidePlayer();
                break;

            case R.id.right_p:
                playNextAudio();
                play_p.setImageResource(R.drawable.pause_vector_gradient);
                break;

            case R.id.play_p:
                 startStop();
                break;

            case R.id.melody_list:
                showNewList();
                break;

            case R.id.random_list:

                if(isLooping){
                    isLooping=false;
                    random_list.setImageResource(R.drawable.repeat_vector_gradient);
                }else{
                    isLooping=true;
                    random_list.setImageResource(R.drawable.random_vector_gradient);
                }

                if(mediaPlayer!=null ){
                    if(mediaPlayer.isPlaying()){
                        Log.i("statusp", mediaPlayer.isLooping()+" st");
                        mediaPlayer.setLooping(isLooping);
                    }
                }


                break;
        }
    }

    public void showNewList(){

        AudioListFragment myFragment = (AudioListFragment)getSupportFragmentManager().findFragmentByTag("LIST_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
        }else{
            AudioListFragment audioListFragment = AudioListFragment.init();
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .add(R.id.container, audioListFragment, "LIST_FRAGMENT").commit();
        }

    }

    private void setMyAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        //Реклама РАБОТАЕТ ПРОСТО ОТКЛЮЧИТЬ ТУТ
        mAdView.loadAd(adRequest);
    }

    public void hideAddView(){
        mAdView.animate().alpha(0f).setDuration(1000).start();
        rv.animate().alpha(0f).setDuration(1000).start();
        player.animate().alpha(0f).setDuration(1000).start();
        audio_name.animate().alpha(0f).setDuration(1000).start();
    }

    public void showAddView(){
        mAdView.animate().alpha(1f).setDuration(1000).start();
        rv.animate().alpha(1f).setDuration(1000).start();
        player.animate().alpha(1f).setDuration(1000).start();
        audio_name.animate().alpha(1f).setDuration(1000).start();

    }

    public void showMainMenu(){
        main_m.animate().translationY(0).setDuration(1000).start();
    }

    public void hideMainMenu(){
        main_m.animate().translationY( main_m.getHeight()).setDuration(1000).start();
    }

    public void showRvMenu(){
        rv.animate().translationY(0).setDuration(1000).start();
    }

    public void hideRvMenu(){
        rv.animate().translationY( rv.getHeight()).setDuration(1000).start();
    }

   public void updateAudioList(){
       arrayList = getAudios();
    }

    private void loopOrRandom(){

        savedAudioFile = "";
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();}
        else{
            play_p.setImageResource(R.drawable.play_vector_gradient);
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extras) {

        Log.i("errorq", mp+"mp");
        Log.i("errorq", what+"what");
        Log.i("errorq", extras+"extras");

        play_p.setImageResource(R.drawable.play_vector_gradient);
        audio_name.setText("");
        Toast.makeText(this, "Sorry, something went wrong", Toast.LENGTH_SHORT).show();

        return true;
    }
}
