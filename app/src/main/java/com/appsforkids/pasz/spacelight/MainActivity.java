package com.appsforkids.pasz.spacelight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.Adapters.RecyclerViewAdapter;
import com.appsforkids.pasz.spacelight.Fragments.AudioListFragment;
import com.appsforkids.pasz.spacelight.Fragments.BgListFragment;
import com.appsforkids.pasz.spacelight.Fragments.ImgeList;
import com.appsforkids.pasz.spacelight.Fragments.LottieList;
import com.appsforkids.pasz.spacelight.Fragments.MainFragment;
import com.appsforkids.pasz.spacelight.Fragments.MessageFragment;
import com.appsforkids.pasz.spacelight.Fragments.SimpleMessageFragment;
import com.appsforkids.pasz.spacelight.Fragments.TimerFragment;
import com.appsforkids.pasz.spacelight.Interfaces.ChangeColors;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnErrorListener {

    MediaPlayer mediaPlayer;
    private static final String MY_SETTINGS = "my_settings";
    private static final String MY_AUDIO = "my_audio";
    String savedAudioFile = "",
            currentLink,
            currentName;

    ArrayList<AudioFile> arrayList;

    SoundPool soundPool;
    private AdView mAdView;
    AdRequest adRequest;

    LinearLayout main_m;

    boolean show = true,
            timerOn = false,
            backgroundTumbler = false,
            isLooping = false,
            chekMenu = true;

    int gradientCounter = 0,
            brights = 0,
            smImage = 0,
            audioCounter = 0;

    CountDownTimer offTimer,
                   hideLockTimer;

    RevolutionAnimationView revolutionAnimationView;
    RecyclerViewAdapter adapter;
    MainFragment mainFragment;
    MyObjects myObjects;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.home_b)
    ImageView home_b;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.suit_button)
    ImageView suit_button;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lockButton)
    LinearLayout lockButton;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.alien_bt)
    LottieAnimationView alien_bt;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rocket)
    LottieAnimationView rocket;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lock_button)
    ImageView lock_button;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lock_frame)
    FrameLayout lock_frame;

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
    ConstraintLayout constrain;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.melody_list)
    ImageView melody_list;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.audio_name)
    public TextView audio_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.timer_text)
    public TextView timer_text;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv)
    public RecyclerView rv;

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
                r.getDisplayMetrics());

        home_b.setOnClickListener(this);
        right_p.setOnClickListener(this);
        play_p.setOnClickListener(this);
        melody_list.setOnClickListener(this);
        random_list.setOnClickListener(this);
        rocket.setOnClickListener(this);
        suit_button.setOnClickListener(this::onClick);
        lock_button.setOnClickListener(this);
        audio_name.setSelected(true);
        alien_bt.setOnClickListener(this);

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
        hideLockTimer = new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                if (chekMenu) {

                } else {
                    lockButton.setVisibility(View.GONE);
                }
            }
        };


        //Блокуванян екрану
        lock_frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                lockButton.setVisibility(View.VISIBLE);
                //suit.setVisibility(View.VISIBLE);
                // hideLockTimer.start();

                return false;
            }
        });


        //Встановлюємо стандартні налаштунки
        if (!isFirstOpen()) {
            assert Realm.getDefaultConfiguration() != null;
            Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
            realm.beginTransaction();
            realm.insert(getDefualtSettings());
            realm.commitTransaction();
            LoadMelody loadMelody = new LoadMelody(this);

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
                        changeBgColor();
                        break;
                    case 2:
                        //bg images
                        changeBackgroundImage();

                        break;
                    case 3:
                        //timer
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.constrain, new TimerFragment())
                                .commit();
                        break;
                    case 4:
                        //animation
                        startAnim();
                        break;
                    case 5:
                        //bright
                        changeBrighest();
                        break;
                    case 6:
                        //suit color
                        mainFragment.changeSuitColor();
                        break;
                    case 7:
                        //politic
                        openPrivatePolicy();
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

    public void playLockalMusic(String link, String name, Boolean play_status) {

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

        } else {
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

        Log.i("statusp", mediaPlayer.isLooping() + " st");

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
        } else {
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
            audio_name.setText(name + ", " + auth);

        } else {
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
            Log.i("playSavedAudio", savedAudio + " savedAudio");
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
        Log.i("playSavedAudio", savedAudio + " refreshSavingAudio");
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

    public void platSPool(int position) {
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

    private ArrayList<AudioFile> getAudios() {

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

    public String playNextAudio() {

        int allAudio = arrayList.size();
        getAudios();

        switch (allAudio) {
            case 0:
                //Have not any audio
                break;
            case 1:
                //Have first audio with id
                if (arrayList.get(audioCounter).getResourceLink() != 0) {
                    playMusic(arrayList.get(audioCounter).getResourceLink(), arrayList.get(audioCounter).nameSong, arrayList.get(audioCounter).authorSong, true);
                    //activeAudio=audioCounter;
                }
                break;

            default:
                //Have melody with lockal link

                if (arrayList.get(audioCounter).getResourceLink() != 0) {
                    playMusic(arrayList.get(audioCounter).getResourceLink(), arrayList.get(audioCounter).nameSong, arrayList.get(audioCounter).authorSong, true);
                    // activeAudio=audioCounter;
                } else {
                    playLockalMusic(
                            arrayList.get(audioCounter).getLockalLink(),
                            arrayList.get(audioCounter).getNameSong() + ", " + arrayList.get(audioCounter).authorSong,
                            true);
                    //activeAudio=audioCounter;
                }
                break;
        }

        audioCounter++;

        Log.i("audioCounter", audioCounter + " rrr " + allAudio);

        if (audioCounter == allAudio) {
            audioCounter = 0;
        }

        return "";
    }

    public Boolean startStop() {
        Log.i("isplay", " startStop");
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                play_p.setImageResource(R.drawable.play_vector_gradient);
                Log.i("isplay", " true");
                return false;

            } else {
                Log.i("isplay", " false");
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    play_p.setImageResource(R.drawable.pause_vector_gradient);
                    Log.i("isplay", mediaPlayer + " false");
                    return true;
                }
            }

        } else {
            play_p.setImageResource(R.drawable.pause_vector_gradient);
            playNextAudio();
            Log.i("isplay", " pause_vector_gradient");
        }
        return false;
    }

    public void playLoopingAudio(Boolean status) {
        isLooping = status;
    }

    public void hidePlayer() {
        player.animate().translationY(player.getHeight()).setDuration(1000);

    }

    public void showPlayer() {
        player.animate().translationY(0).setDuration(1000);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                if (isLooping) {
                    isLooping = false;
                    random_list.setImageResource(R.drawable.repeat_vector_gradient);
                } else {
                    isLooping = true;
                    random_list.setImageResource(R.drawable.random_vector_gradient);
                }
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        Log.i("statusp", mediaPlayer.isLooping() + " st");
                        mediaPlayer.setLooping(isLooping);
                    }
                }
                break;
            case R.id.alien_bt:
                clickAlian();
                break;
            case R.id.rocket:
                clickRocket();
                break;
            case R.id.lock_button:
                lockButton();
                break;
            case R.id.suit_button:
                mainFragment = new MainFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.container, mainFragment)
                        .commit();
                break;
        }
    }

    public void showNewList() {

        AudioListFragment myFragment = (AudioListFragment) getSupportFragmentManager().findFragmentByTag("LIST_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
        } else {
            AudioListFragment audioListFragment = AudioListFragment.init();
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .add(R.id.constrain, audioListFragment, "LIST_FRAGMENT").commit();
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

    public void hideAddView() {
        mAdView.animate().alpha(0f).setDuration(1000).start();
        rv.animate().alpha(0f).setDuration(1000).start();
        player.animate().alpha(0f).setDuration(1000).start();
        audio_name.animate().alpha(0f).setDuration(1000).start();
    }

    public void showAddView() {
        mAdView.animate().alpha(1f).setDuration(1000).start();
        rv.animate().alpha(1f).setDuration(1000).start();
        player.animate().alpha(1f).setDuration(1000).start();
        audio_name.animate().alpha(1f).setDuration(1000).start();

    }

    public void showMainMenu() {
        main_m.animate().translationY(0).setDuration(1000).start();
    }

    public void hideMainMenu() {
        main_m.animate().translationY(main_m.getHeight()).setDuration(1000).start();
    }

    public void showRvMenu() {
        rv.animate().translationY(0).setDuration(1000).start();
    }

    public void hideRvMenu() {
        rv.animate().translationY(rv.getHeight()).setDuration(1000).start();
    }

    public void updateAudioList() {
        arrayList = getAudios();
    }

    private void loopOrRandom() {

        savedAudioFile = "";
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else {
            play_p.setImageResource(R.drawable.play_vector_gradient);
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extras) {

        Log.i("errorq", mp + "mp");
        Log.i("errorq", what + "what");
        Log.i("errorq", extras + "extras");

        play_p.setImageResource(R.drawable.play_vector_gradient);
        audio_name.setText("");
        Toast.makeText(this, "Sorry, something went wrong", Toast.LENGTH_SHORT).show();

        return true;
    }

    private void clickAlian() {
        LottieList lottieFragment = new LottieList();
        lottieFragment.setCallBack(new MainFragment.ChoseItem() {
            @Override
            public void setImage(String link) {
                //lottie.setAnimationFromUrl(link);
                // lottie.playAnimation();
                // lottie.setSpeed(1f);
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, lottieFragment, "LOTTIE_FRAGMENT")
                .commit();

        rocket.animate().alpha(0f).start();
        alien_bt.animate().alpha(0f).start();
    }

    private void clickRocket() {
        //Analistic
        // sendAnalystics("rocket", "press_rocekt");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        //rocket.animate().translationY(-height).setDuration(2000).start();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_from_down, R.anim.slide_in_up)
                .add(R.id.container, new ImgeList())
                .commit();

        rocket.animate().alpha(0f).start();
        alien_bt.animate().alpha(0f).start();

    }

    public void lockButton() {

        // Fragment myFragment = getParentFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
        // final TextView textView = myFragment.getView().findViewById(R.id.nameNightlight);

        if (chekMenu) {
            //openMenu(menuItems.getMenuButtons(colors));
            // textView.animate().alpha(0).setDuration(1000).start();
            //timerText.animate().alpha(0).setDuration(1000).start();
            //suit_button.setVisibility(View.GONE);
            suit_button.animate().alpha(0).setDuration(1000).start();

            lock_button.setImageResource(R.drawable.lock_vector_gradient);
            lockButton.setAlpha(0.3f);
            lock_frame.setClickable(true);

            rocket.animate().alpha(0).setDuration(1000).start();
            alien_bt.animate().alpha(0).setDuration(1000).start();

            hideAddView();

            mainFragment.hideElements();


            chekMenu = false;
            show = false;

            hideLockTimer.start();

        } else {
            //textView.animate().alpha(1f).setDuration(1000).start();
            //suit_button.setVisibility(View.VISIBLE);
            suit_button.animate().alpha(1f).setDuration(1000).start();

            rocket.animate().alpha(1f).setDuration(1000).start();
            alien_bt.animate().alpha(1f).setDuration(1000).start();

            mainFragment.showElements();

            rocket.setVisibility(View.VISIBLE);

            showAddView();


            lockButton.setAlpha(1f);
            if (timerOn) {
                // timerText.animate().alpha(1f).setDuration(1000).start();
            } else {

            }

            lock_button.setImageResource(R.drawable.unlock_vector_gradient);
            lock_frame.setClickable(false);
            chekMenu = true;
            show = true;

            //Реклама РАБОТАЕТ ПРОСТО ОТКЛЮЧИТЬ ТУТ
//            if (mInterstitialAd != null) {
//                mInterstitialAd.show(getActivity());
//                loadAddFullScrean();
//            } else {
//                Log.d("TAG", "The interstitial ad wasn't ready yet.");
//            }

        }

        // sendAnalystics("lock", "lock is: "+chekMenu);

    }

    public void openPrivatePolicy() {
        MessageFragment myFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag("POLICY_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MessageFragment.init(getString(R.string.open_policy), new DoThisAction() {
                        @Override
                        public void doThis() {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://paszzsap.github.io/nightlight2/politic.html"));

                            if (hasConnection()) {


                                startActivity(browserIntent);
                            } else {
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_1)))
                                        .commit();
                            }
                        }

                        @Override
                        public void doThis(int hours, int minutes) {
                        }

                        @Override
                        public void doThat() {
                        }
                    }), "POLICY_FRAGMENT")
                    .commit();
        }

        //sendAnalystics("politica", "press");
    }

    public Boolean hasConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            // sendAnalystics("internet", "WIFI");
            return true;
        } else {

        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            // sendAnalystics("internet", "MOBILE");
            return true;
        } else {

        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        } else {

        }
        // sendAnalystics("internet", "NO INTERNET");
        return false;
    }

    public void changeBgColor() {

        gradientCounter++;
        if (gradientCounter >= myObjects.getGradient().length) {
            gradientCounter = 0;
        }
        constrain.setBackgroundResource(myObjects.getGradient()[gradientCounter]);
        backgroundTumbler = true;

        //sendAnalystics("changeBgColor", "changeBgColor: "+gradientCounter);
    }

    public void changeBackgroundImage() {
        BgListFragment bgListFragment = new BgListFragment();
        bgListFragment.setCallBack(new MainFragment.ChoseItem() {
            @Override
            public void setImage(String link) {
                Picasso.get().load(link).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        constrain.setBackground(new BitmapDrawable(getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, bgListFragment)
                .commit();
    }

    public void startAnim() {

        if (smImage == -1) {

        } else {

            startAnimation2(myObjects.getAnimationImage()[smImage]);
            // Toast.makeText(this, "We are here "+ myObjects.getAnimationImage()[smImage], Toast.LENGTH_SHORT).show();
            smImage++;
            if (smImage >= myObjects.getAnimationImage().length) {
                deleteAnimation();
                smImage = 0;
            }
        }
    }

    public void startAnimation2(int imageViewAnimation) {
        Log.i("startAnimation2", imageViewAnimation + "");
        if (revolutionAnimationView == null) {
            //Создаем анимацию
            revolutionAnimationView = new RevolutionAnimationView(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                revolutionAnimationView.setZ(-1);
            }
            constrain.addView(revolutionAnimationView);
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(this, imageViewAnimation));

        } else {
            if (revolutionAnimationView.getParent() == null) {
                constrain.addView(revolutionAnimationView);
            }
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(this, imageViewAnimation));
            Log.i("startAnimation2", imageViewAnimation + " tyt");
        }

        //sendAnalystics("start_anim", "animation is= " + imageViewAnimation);
    }

    public void deleteAnimation() {
        //Удаляем созданную вьюху с анимацией
        ((ViewManager) revolutionAnimationView.getParent()).removeView(revolutionAnimationView);
        Log.i("anim", "анимация удалена");

    }

    public void changeBrighest() {

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = myObjects.getBrights()[brights];
        getWindow().setAttributes(layout);
        brights++;

        if (brights >= myObjects.getBrights().length) {
            brights = 0;
        }

        //sendAnalystics("bright", "bright="+brights);
    }

}
