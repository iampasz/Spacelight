package com.appsforkids.pasz.spacelight.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.appsforkids.pasz.spacelight.Adapters.MyPagerAdapter;
import com.appsforkids.pasz.spacelight.Adapters.RecyclerViewAdapter;
import com.appsforkids.pasz.spacelight.AddToRealm;
import com.appsforkids.pasz.spacelight.Interfaces.ChangeColors;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.MyObjects;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.appsforkids.pasz.spacelight.RevolutionAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainFragment extends Fragment implements Serializable, View.OnClickListener {



    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.lock_button)
    ImageView lock_button;

    @BindView(R.id.lockButton)
    LinearLayout lockButton;

    @BindView(R.id.suit)
    ImageView suit;

    @BindView(R.id.presents)
    ImageView presents;

    @BindView(R.id.animateBg)
    ImageView animateBg;

    @BindView(R.id.timer)
    TextView timerText;

    ConstraintLayout mainBg;

    @BindView(R.id.main_constrain)
    ConstraintLayout main_constrain;

    @BindView(R.id.lock_frame)
    FrameLayout lock_frame;

    @BindView(R.id.rv)
    public RecyclerView rv;

    MyPagerAdapter pagerAdapter;

    RecyclerViewAdapter adapter;

    Boolean show = true;
    Boolean timerOn = false;
    Boolean backgroundTumbler = false;

    public RevolutionAnimationView revolutionAnimationView;

    Context ctx;
    boolean chekMenu = true;
    boolean chekStatus = true;

    boolean playerStatus =  true;

    Drawable mDrawable;

    String[] colors;
    String[] bgColors;
    String[] bgNlColors;
    String[] menuColors;

    int suitCounter = 0;
    int gradientCounter = 0;
    int anim_bg = 0;
    int brights = 0;
    int smImage = -1;

    String MY_CHEKBOX = "my_chekbox";

    ImageView suitColor;
    MyObjects myObjects;
    CountDownTimer offTimer;
    CountDownTimer hideLockTimer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, null);
        ButterKnife.bind(this, view);

        mainBg = ((MainActivity)getActivity()).constrain;

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = view.getContext();

        myObjects = new MyObjects(getContext());

        pagerAdapter = new MyPagerAdapter(getParentFragmentManager(), myObjects.getNightlighters());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(500);



        float dip = 70f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );



        //Налаштунки
        setSettings();

        //Оновлення мелодій з інтернету
        AddToRealm addToRealm = new AddToRealm();

        switch (hasConnection(getContext())) {
            case 0:
                break;
            case 1:
                addToRealm.addJsonToRealm();
                break;
            case 2:
                addToRealm.addJsonToRealm();
                break;
            case 3:
                addToRealm.addJsonToRealm();
                break;
        }

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(llm);
        Resources res = getResources();

        colors = res.getStringArray(R.array.myColors);
        bgColors = res.getStringArray(R.array.bgColors);
        bgNlColors = res.getStringArray(R.array.bgNlColors);
        menuColors = res.getStringArray(R.array.menuColors);

        hideLockTimer = new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                lockButton.setVisibility(View.GONE);
            }
        };

        //Блокуванян екрану
        lock_frame.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                lockButton.setVisibility(View.VISIBLE);
                //suit.setVisibility(View.VISIBLE);
                hideLockTimer.start();

                return false;
            }
        });

        //Кнопка костюму
        suit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
                suitColor = myFragment.getView().findViewById(R.id.suit_color);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        suitColor.setVisibility(View.INVISIBLE);
                        Log.i("Show", "imageView.setVisibility(View.INVISIBLE);");
                        break;
                    case MotionEvent.ACTION_UP:

                        suitColor.setVisibility(View.VISIBLE);
                        Log.i("Show", "imageView.setVisibility(View.VISIBLE);");
                        break;

                }
                return true;
            }
        });

        //Кнопка бокування
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockButton();
            }
        });

        //Кнопки меню
        adapter = new RecyclerViewAdapter(myObjects.getMenuButtons(colors), menuColors);
        adapter.MyOnclick(new ChangeColors() {
            @Override
            public void onclick(int button) {
                switch (button) {
                    case 1:
                       // getParentFragmentManager().beginTransaction().add(R.id.container, new MelodyListFragment(), "MelodyListFragment").commit();
                        if(playerStatus){
                            ((MainActivity)getActivity()).hidePlayer();
                            rv.animate().alpha(1f).setDuration(1000);
                            playerStatus=false;
                        }else{

                            //rv.setVisibility(View.GONE);
                            rv.animate().alpha(0f).setDuration(1000);
                            ((MainActivity)getActivity()).showPlayer();
                            playerStatus=true;
                        }

                        break;
                    case 2:
                        changeBgColor();
                        break;
                    case 3:
                        changeSuitColor();
                        break;
                    case 4:
                        if (smImage == -1) {
                        } else {
                            startAnimation2(myObjects.getAnimationImage()[smImage]);
                            smImage++;
                            if (smImage >= myObjects.getAnimationImage().length) {
                                deleteAnimation();
                                smImage = 0;
                            }
                        }
                        break;
                    case 5:
                        setTimer();
                        break;
                    case 6:
                        switch (hasConnection(getContext())) {
                            case 0:
                                    getParentFragmentManager()
                                            .beginTransaction()
                                            .add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_1)))
                                            .commit();
                                break;
                            case 1:
                                changeBackgroundImage();
                                break;
                            case 2:

                                    getParentFragmentManager()
                                            .beginTransaction()
                                            .add(R.id.container, MessageFragment.init(getResources().getString(R.string.message_3), new DoThisAction() {
                                                @Override
                                                public void doThis() {
                                                    changeBackgroundImage();
                                                }
                                                @Override
                                                public void doThis(int hours, int minutes) {
                                                }
                                                @Override
                                                public void doThat() {

                                                }
                                            }), "MelodyListFragment")
                                            .commit();


                                break;
                            case 3:
                                changeBackgroundImage();
                                break;
                        }
                        break;
                    case 7:
                        changeBrighest();
                        break;
                    case 8:
                        openPrivatePolicy();
                        break;
                }
            }
        });
        rv.setAdapter(adapter);
    }

    public void changeSuitColor() {

        Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
        suitColor = myFragment.getView().findViewById(R.id.suit_color);

        suitCounter++;
        if (suitCounter >= myObjects.getGradientArray().length) {
            suitCounter = 0;
        }

        suitColor.setImageResource(myObjects.getGradientArray()[suitCounter]);

    }

    public void changeBrighest() {

        WindowManager.LayoutParams layout = getActivity().getWindow().getAttributes();
        layout.screenBrightness = myObjects.getBrights()[brights];
        getActivity().getWindow().setAttributes(layout);
        brights++;

        if (brights >= myObjects.getBrights().length) {
            brights = 0;
        }
    }

    public void turnOff() {
        ((MainActivity) getActivity()).playMusic(0, false);
        getActivity().finish();
    }

    public void lockButton() {

        Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
        final TextView textView = myFragment.getView().findViewById(R.id.nameNightlight);

        if (chekMenu) {
            //openMenu(menuItems.getMenuButtons(colors));
            rv.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.GONE);
            timerText.setVisibility(View.GONE);
            suit.setVisibility(View.GONE);
            lock_button.setImageResource(R.drawable.ic_lock);
            lockButton.setAlpha(0.3f);
            lock_frame.setClickable(true);


            ((MainActivity)getActivity()).hideAddView();

            chekMenu = false;
            show = false;

            hideLockTimer.start();


        } else {
            rv.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            suit.setVisibility(View.VISIBLE);

            ((MainActivity)getActivity()).showAddView();

            lockButton.setAlpha(1f);
            if (timerOn) {
                timerText.setVisibility(View.VISIBLE);
            } else {

            }

            lock_button.setImageResource(R.drawable.ic_unlock);
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

    }

    public void startAnimation2(int imageViewAnimation) {
        Log.i("startAnimation2", imageViewAnimation + "");
        if (revolutionAnimationView == null) {
            //Создаем анимацию
            revolutionAnimationView = new RevolutionAnimationView(getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                revolutionAnimationView.setZ(-1);
            }
            mainBg.addView(revolutionAnimationView);
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), imageViewAnimation));

        } else {
            if (revolutionAnimationView.getParent() == null) {
                mainBg.addView(revolutionAnimationView);
            }
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), imageViewAnimation));
            Log.i("startAnimation2", imageViewAnimation + " tyt");
        }
    }

    public void deleteAnimation() {
        //Удаляем созданную вьюху с анимацией
        ((ViewManager) revolutionAnimationView.getParent()).removeView(revolutionAnimationView);
        Log.i("anim", "анимация удалена");

    }

    public void startTimer(int hours, int minutes) {
        if (hours == 0 && minutes == 0) {
            timerText.setVisibility(View.INVISIBLE);
        } else {
            timerText.setVisibility(View.VISIBLE);
            int mySeconds = (((hours * 60 * 60) + (60 * minutes)) * 1000);
            offTimer = new CountDownTimer(mySeconds, 1000) {
                @Override
                public void onTick(long l) {

                    timerText.setText(String.format("%02d:%02d:%02d", (l / 1000) / 3600, ((l / 1000) % 3600) / 60, (l / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    Log.i("FINISH", "App is OFF");
                    turnOff();
                }
            };
            offTimer.start();
        }

    }

    public void openPrivatePolicy() {
            getParentFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MessageFragment.init(getString(R.string.open_policy), new DoThisAction() {
                            @Override
                            public void doThis() {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://paszzsap.github.io/nightlight2/politic.html"));

                                switch (hasConnection(getContext())) {
                                    case 0:
                                        getParentFragmentManager()
                                                .beginTransaction()
                                                .add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_1)))
                                                .commit();
                                        break;
                                    case 1:
                                        startActivity(browserIntent);
                                        break;
                                    case 2:
                                        startActivity(browserIntent);

                                        break;
                                    case 3:
                                        changeBackgroundImage();
                                        break;
                                }


                            }

                            @Override
                            public void doThis(int hours, int minutes) {

                            }

                            @Override
                            public void doThat() {
                            }
                        }), "MelodyListFragment")
                    .commit();
    }

    public void setTimer() {

        if (offTimer != null) {
            offTimer.cancel();
            timerText.setVisibility(View.INVISIBLE);
            timerOn = false;
        }

        getParentFragmentManager().beginTransaction().add(R.id.container, TimerFragment.init(new DoThisAction() {
            @Override
            public void doThis() {

            }

            @Override
            public void doThis(int hours, int minutes) {

                startTimer(hours, minutes);
                timerOn = true;
            }

            @Override
            public void doThat() {

            }
        })).commit();

    }

    public void changeBgColor() {

        gradientCounter++;
        if (gradientCounter >= myObjects.getGradient().length) {
            gradientCounter = 0;
        }
        mainBg.setBackgroundResource(myObjects.getGradient()[gradientCounter]);


        backgroundTumbler = true;

    }

    public void changeBackgroundImage() {

        BackgroundFragment backgroundFragment = new BackgroundFragment();
        backgroundFragment.setCallBack(new ChoseItem() {
            @Override
            public void setImage(String link) {
                Picasso.get().load(link).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mainBg.setBackground(new BitmapDrawable(getResources(), bitmap));
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

        getParentFragmentManager().beginTransaction().add(R.id.container, backgroundFragment).commit();

    }

    @Override
    public void onStop() {
        super.onStop();
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setNightlightPosition(pager.getCurrentItem());
        settings.setBackgroundColor(anim_bg);
        settings.setGradientColor(gradientCounter);
        settings.setBackgroundTumbler(backgroundTumbler);
        settings.setAnimationPosition(smImage);

        realm.commitTransaction();
    }

    public void setSettings() {

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();

if(settings!=null){
    pager.setCurrentItem(settings.getNightlightPosition(), false);
    smImage = settings.getAnimationPosition();
    gradientCounter = settings.getGradientColor();
    anim_bg = settings.getBackgroundColor();
    backgroundTumbler = settings.getBackgroundTumbler();

    if (backgroundTumbler) {
        mainBg.setBackgroundResource(myObjects.getGradient()[gradientCounter]);
    } else {
        mainBg.setBackgroundResource(myObjects.getBackground()[anim_bg]);
    }

    //Устанавливаем анимацию
    if (smImage != 0) {
        startAnimation2(myObjects.getAnimationImage()[smImage]);
    }
}


        realm.commitTransaction();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.time_b:

                setTimer();

                break;
            case R.id.anim_b:

                if (smImage == -1) {
                } else {
                    startAnimation2(myObjects.getAnimationImage()[smImage]);
                    smImage++;
                    if (smImage >= myObjects.getAnimationImage().length) {
                        deleteAnimation();
                        smImage = 0;
                    }
                }

                break;
            case R.id.light_b:
                changeBrighest();
                break;


            case R.id.image_b:
                changeBackgroundImage();
                break;

            case R.id.suit_b:
                changeSuitColor();
                break;

            case R.id.paint2_b:
                changeBgColor();
                break;

            case R.id.anim2_b:

                int image = myObjects.getAnimationImage()[smImage];
                revolutionAnimationView.changeColorImage(ContextCompat.getDrawable(getContext(), image), Color.YELLOW );
                break;

        }

    }

    public interface ChoseItem {

        public void setImage(String link);
    }

    public int hasConnection(final Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 3;
        } else {

        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 2;
        } else {

        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 1;
        } else {

        }
        return 0;
    }

//    private boolean getChekBoxStatus(String preferences) {
//        SharedPreferences spa = getActivity().getSharedPreferences(MY_CHEKBOX, Context.MODE_PRIVATE);
//        chekStatus = spa.getBoolean(preferences, true);
//
//        if (chekStatus) {
//            Log.i("chhh", "true");
//            return true;
//        } else {
//            Log.i("chhh", "false");
//            return false;
//        }
//    }
//
//    public void setChekBoxStatus(String preferences) {
//        SharedPreferences spa = getActivity().getSharedPreferences(MY_CHEKBOX, Context.MODE_PRIVATE);
//        // выводим нужную активность
//        SharedPreferences.Editor e = spa.edit();
//        e.putBoolean(preferences, false);
//        e.commit(); // не забудьте подтвердить изменения
//    }

    private void showPlayer(){

        ((MainActivity)getActivity()).showPlayer();

    }





    public void changeAnimColor(){

        int image = myObjects.getAnimationImage()[smImage];
        revolutionAnimationView.changeColorImage(ContextCompat.getDrawable(getContext(), image), Color.YELLOW );
    }

    public void  startAnim(){
        if (smImage == -1) {
        } else {
            startAnimation2(myObjects.getAnimationImage()[smImage]);
            smImage++;
            if (smImage >= myObjects.getAnimationImage().length) {
                deleteAnimation();
                smImage = 0;
            }
        }
    }
}
