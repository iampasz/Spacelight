package  com.appsforkids.pasz.spacelight.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.appsforkids.pasz.spacelight.Adapters.MyPagerAdapter;
import com.appsforkids.pasz.spacelight.Adapters.RecyclerViewAdapter;
import com.appsforkids.pasz.spacelight.Fabrica.MyMenuItems;
import com.appsforkids.pasz.spacelight.Interfaces.ChangeColors;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.MyObjects;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.appsforkids.pasz.spacelight.RevolutionAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainFragment extends Fragment {

    @BindView(R.id.pager )
    ViewPager pager;

    @BindView(R.id.lock_button )
    ImageView lock_button;

    @BindView(R.id.lockButton )
    LinearLayout lockButton;

    @BindView(R.id.suit )
    ImageView suit;

    @BindView(R.id.presents )
    ImageView presents;

    @BindView(R.id.animateBg )
    ImageView animateBg;

    @BindView(R.id.timer )
    TextView timerText;

    @BindView(R.id.mainBg )
    FrameLayout mainBg;

    @BindView(R.id.main_constrain )
    ConstraintLayout main_constrain;

    @BindView(R.id.lock_frame )
    FrameLayout lock_frame;

    @BindView(R.id.rv )
    RecyclerView rv;

    MyPagerAdapter pagerAdapter;
   // MediaPlayer mediaPlayer;

    private AdView mAdView;

    RecyclerViewAdapter adapter;

    Boolean show = true;
    Boolean timerOn = false;
    Boolean backgroundTumbler = false;

    public RevolutionAnimationView revolutionAnimationView;

    Context ctx;
    MyMenuItems menuItems;
    boolean chekMenu = true;

    String[] colors;
    String[] bgColors;
    String[] bgNlColors;
    String[] menuColors;

    int suitCounter = 0;
    int gradientCounter = 0;
    int anim_bg = 0;
    int brights = 0;
    int smImage = -1;

    ImageView suitColor;
    Realm realm;
    boolean showed;
    MyObjects myObjects;
    CountDownTimer offTimer;

    CountDownTimer hideLockTimer;

    private InterstitialAd mInterstitialAd;

    AdRequest adRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = view.getContext();
        FragmentActivity main = getActivity();

        menuItems = new MyMenuItems();

       // mySuitColor = myObjects.getGradientArray()[suitCounter];
        pagerAdapter = new MyPagerAdapter(getParentFragmentManager(), menuItems.getNightlighters());

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(500);

        myObjects = new MyObjects(getContext());

        mAdView = view.findViewById(R.id.adView);

        setMyAds();

        setSettings();

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
                //suit.setVisibility(View.GONE);
            }
        };




      //  pager.setZ(1);
      //  suit.setZ(1);
       // lockButton.setZ(1);
       // lock_frame.setZ(1);

        lock_frame.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                lockButton.setVisibility(View.VISIBLE);
                //suit.setVisibility(View.VISIBLE);
                hideLockTimer.start();

                return false;
            }
        });
        suit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
                suitColor = myFragment.getView().findViewById(R.id.suit_color);

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        suitColor.setVisibility(View.INVISIBLE);
                        Log.i("Show","imageView.setVisibility(View.INVISIBLE);");
                        break;
                    case MotionEvent.ACTION_UP:

                        suitColor.setVisibility(View.VISIBLE);
                        Log.i("Show","imageView.setVisibility(View.VISIBLE);");
                        break;

                }
                return true;
            }
        });
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockButton();
            }
        });

        Realm.init(ctx);
        realm = Realm.getDefaultInstance();
        adapter = new RecyclerViewAdapter(menuItems.getMenuButtons(colors), menuColors);
        adapter.MyOnclick(new ChangeColors() {
            @Override
            public void onclick(int button) {
                switch (button) {
                    case 1:
                        //playSound();
                        setMelody();
                        break;
                    case 2:
                        changeBgColor();
                        break;
                    case 3:
                        changeSuitColor();
                        break;
                    case 4:
                        if(smImage==-1){

                        }else{
                            startAnimation2(myObjects.getAnimationImage()[smImage]);
                            smImage++;
                            if(smImage>=myObjects.getAnimationImage().length){
                                deleteAnimation();
                                smImage=0;
                            }
                        }

                        break;
                    case 6:
                        changeBackgroundImage();
                        break;
                    case 5:
                        setTimer();
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

         adRequest = new AdRequest.Builder().build();

        loadAddFullScrean();
    }

    private void changeSuitColor() {

        Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
        suitColor = myFragment.getView().findViewById(R.id.suit_color);

        suitCounter++;
        if (suitCounter >=myObjects.getGradientArray().length){
            suitCounter = 0;
        }

        suitColor.setImageResource(myObjects.getGradientArray()[suitCounter]);

    }

    public void changeBrighest() {

        WindowManager.LayoutParams layout = getActivity().getWindow().getAttributes();
        layout.screenBrightness = myObjects.getBrights()[brights];
        getActivity().getWindow().setAttributes(layout);
        brights++;

        if(brights>=myObjects.getBrights().length){
            brights=0;
        }
    }

    public void turnOff() {
        ((MainActivity)getActivity()).playMusic(0, false);
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

            mAdView.setVisibility(View.GONE);

            chekMenu = false;
            show = false;

            hideLockTimer.start();


        } else {
            rv.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            suit.setVisibility(View.VISIBLE);
            mAdView.setVisibility(View.VISIBLE);
            lockButton.setAlpha(1f);
            if(timerOn){
                timerText.setVisibility(View.VISIBLE);
            }else{

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

    private void lockFrame() {
        if (chekMenu) {
            rv.setVisibility(View.VISIBLE);
        }
        lockButton.setVisibility(View.VISIBLE);
        hideLockTimer.start();
    }

    public void startAnimation2(int imageViewAnimation) {
        Log.i("startAnimation2", imageViewAnimation+"");
        if(revolutionAnimationView==null){
            //Создаем анимацию
            revolutionAnimationView = new RevolutionAnimationView(getContext());
            revolutionAnimationView.setZ(-1);
            main_constrain.addView(revolutionAnimationView);
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(),  imageViewAnimation));

        }else{
            if(revolutionAnimationView.getParent() == null) {
                mainBg.addView(revolutionAnimationView);
            }
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(),  imageViewAnimation));
            Log.i("startAnimation2", imageViewAnimation+" tyt");
        }
    }

    public void deleteAnimation(){
        //Удаляем созданную вьюху с анимацией
        ((ViewManager)revolutionAnimationView.getParent()).removeView(revolutionAnimationView);
        Log.i("anim", "анимация удалена");

    }
    private void showAd() {

        new AlertDialog.Builder(ctx)
                .setTitle("Open Nightlight")
                .setMessage("Open nightlight for advertising")
                .setIcon(R.drawable.presentold)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        if (mInterstitialAd.isLoaded()) {
//                            mInterstitialAd.show();
//                        } else {
//                            Log.d("TAG", "The interstitial wasn't loaded yet.");
//                        }

                        showed = true;
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pager.setCurrentItem(500);
                    }
                })
                .setCancelable(true)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        pager.setCurrentItem(500);
                    }
                })
                .show();

    }
    public void randomColor() {
       // int intColor;
       // Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
       // imageView = myFragment.getView().findViewById(R.id.suit_color);
       // Random random = new Random();
       // intColor = random.nextInt(bgNlColors.length);
       //imageView.setColorFilter(Color.parseColor(bgNlColors[intColor]));
    }

    public void changeRandomColor() {
        Random random = new Random();
        int NLRColor = random.nextInt(8);
        int BGNLRColor = random.nextInt(8);
        Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
    }

    private void startTimer(int hours, int minutes){
        if(hours==0 && minutes==0){
            timerText.setVisibility(View.INVISIBLE);
        }else{
            timerText.setVisibility(View.VISIBLE);
            int mySeconds = (((hours*60*60)+(60*minutes))*1000);
            offTimer =  new CountDownTimer(mySeconds, 1000) {
                @Override
                public void onTick(long l) {

                    timerText.setText(String.format("%02d:%02d:%02d", (l/1000)/3600, ((l/1000)%3600)/60, (l/1000)%60));
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

    private void openPrivatePolicy(){
        getParentFragmentManager().beginTransaction().add(R.id.container,  MessageFragment.init(getString(R.string.open_policy), new DoThisAction() {
            @Override
            public void doThis() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://paszzsap.github.io/nightlight2/politic.html"));
                startActivity(browserIntent);
            }

            @Override
            public void doThis(int hours, int minutes) {

            }
        }), "MelodyListFragment").commit();
    }

    private void setTimer(){

        if(offTimer!=null){
            offTimer.cancel();
            timerText.setVisibility(View.INVISIBLE);
            timerOn=false;
        }

        getParentFragmentManager().beginTransaction().add(R.id.container, TimerFragment.init(new DoThisAction() {
            @Override
            public void doThis() {

            }

            @Override
            public void doThis(int hours, int minutes) {

                startTimer(hours, minutes);
                timerOn=true;
            }
        })).commit();

    }

    private void changeBgColor(){

        gradientCounter++;
        if(gradientCounter>=myObjects.getGradient().length){
            gradientCounter = 0;
        }
        mainBg.setBackgroundResource(myObjects.getGradient()[gradientCounter]);

        backgroundTumbler=true;

    }

    private void changeBackgroundImage(){

        anim_bg++;

        if(anim_bg>=myObjects.getBackground().length){
            anim_bg = 0;
        }
        mainBg.setBackgroundResource(myObjects.getBackground()[anim_bg]);
        backgroundTumbler=false;

    }

    private void setMelody(){
        getParentFragmentManager().beginTransaction().add(R.id.container, new MelodyListFragment(), "MelodyListFragment").commit();
       // getParentFragmentManager().beginTransaction().add(R.id.container, new BackgroundsFragment(), "BackgroundsFragment").commit();
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

    public void setSettings(){

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        Log.i("Setting", "текущая сохраненная позиция"+ settings.getNightlightPosition());
        pager.setCurrentItem(settings.getNightlightPosition(), false);

        //ConstraintLayout constraintLayout = getActivity().findViewById(R.id.main_constrain);

        smImage = settings.getAnimationPosition();
        int currentRate = settings.getRate();
        gradientCounter = settings.getGradientColor();
        anim_bg = settings.getBackgroundColor();
        int currentNightlightPosition = settings.getNightlightPosition();
        int currentNightligh = settings.getNightlight();
        boolean currentAdds = settings.getAdds();
        float currentBright = settings.getBright();
        backgroundTumbler = settings.getBackgroundTumbler();




        if(backgroundTumbler){
            mainBg.setBackgroundResource(myObjects.getGradient()[gradientCounter]);
        }else{
            mainBg.setBackgroundResource(myObjects.getBackground()[anim_bg]);
        }


        //Показываем фрагмент где предлагается проголосовать за приложение и поставить оценку
        switch (currentRate){
            case -1:

                break;

            case 3:
                //проверяем интернет подключение
//                if(hasConnection(getContext())){
//                    getParentFragmentManager()
//                            .beginTransaction()
//                            .setCustomAnimations(R.animator.appearance, R.animator.disappearance)
//                            .add(R.id.main, new RateFragment())
//                            .commit();
//
//                    settings.setRate(0);
//                }

                break;

            default:
                currentRate++;
                settings.setRate(currentRate);
                break;
        }

        //Устанавливаем анимацию
        Log.i("setSettings"," currentAnimation " + smImage);
        if(smImage!=0){
            startAnimation2(myObjects.getAnimationImage()[smImage]);
        }

        // ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), myObjects.getAnimationImage()[currentAnimation]));
        realm.commitTransaction();
    }

    private void loadAddFullScrean(){
        InterstitialAd.load(getContext(),"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("Add", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("Add", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }

    private void setMyAds(){

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        AdRequest adRequest = new AdRequest.Builder().build();
        //Реклама РАБОТАЕТ ПРОСТО ОТКЛЮЧИТЬ ТУТ
        mAdView.loadAd(adRequest);
    }

}
