package com.appsforkids.pasz.spacelight.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.appsforkids.pasz.spacelight.Adapters.MyPagerAdapter;
import com.appsforkids.pasz.spacelight.AddToRealm;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.MyObjects;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.Serializable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainFragment extends Fragment implements Serializable {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager pager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.main_constrain)
    ConstraintLayout main_constrain;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tab_l2)
    TabLayout tab_l2;
    //Viwes from fragment from ViewPager
    MyPagerAdapter pagerAdapter;
    Context ctx;
    ImageView image;
    MyObjects myObjects;
    private FirebaseAnalytics mFirebaseAnalytics;

    int suitCounter = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = getContext();
        myObjects = new MyObjects(ctx);

        pagerAdapter = new MyPagerAdapter(getParentFragmentManager(), myObjects.getNightlighters());

        pager.setAdapter(pagerAdapter);
        tab_l2.setupWithViewPager(pager, true);

        float dip = 70f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
    }



    public void changeSuitColor() {
        Fragment myFragment = getFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
      ImageView  suitColor = myFragment.getView().findViewById(R.id.suit_color);

        if(suitColor!=null){
            if(suitColor.isShown()){

                suitCounter++;
                if (suitCounter >= myObjects.getGradientArray().length) {
                    suitCounter = 0;
                }
                suitColor.setImageResource(myObjects.getGradientArray()[suitCounter]);
            }
        }
        sendAnalystics("change_color", "color"+suitCounter);
    }








    @Override
    public void onStop() {
        super.onStop();
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
//        settings.setNightlightPosition(pager.getCurrentItem());
//        settings.setBackgroundColor(anim_bg);
//        settings.setGradientColor(gradientCounter);
 //       settings.setBackgroundTumbler(backgroundTumbler);
 //       settings.setAnimationPosition(smImage);

        realm.commitTransaction();
    }

//    public void setSettings() {
//
//        Realm.init(getContext());
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        MySettings settings = realm.where(MySettings.class).findFirst();
//
//if(settings!=null){
//    pager.setCurrentItem(settings.getNightlightPosition(), false);
//    smImage = settings.getAnimationPosition();
//    gradientCounter = settings.getGradientColor();
//    anim_bg = settings.getBackgroundColor();
//    backgroundTumbler = settings.getBackgroundTumbler();
//
//    if (backgroundTumbler) {
//        mainBg.setBackgroundResource(myObjects.getGradient()[gradientCounter]);
//    } else {
//        mainBg.setBackgroundResource(myObjects.getBackground()[anim_bg]);
//    }
//
//    //Устанавливаем анимацию
//    if (smImage != 0) {
//        startAnimation2(myObjects.getAnimationImage()[smImage]);
//    }
//}
//        realm.commitTransaction();
//    }
//


    public interface ChoseItem {

        public void setImage(String link);
    }

    public int hasConnection(final Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            sendAnalystics("internet", "WIFI");
            return 3;
        } else {

        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            sendAnalystics("internet", "MOBILE");
            return 2;
        } else {

        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 1;
        } else {

        }
        sendAnalystics("internet", "NO INTERNET");
        return 0;
    }

    private void showPlayer(){
        ((MainActivity)getActivity()).showPlayer();

    }

//    public void changeAnimColor(){
//
//        int image = myObjects.getAnimationImage()[smImage];
//        revolutionAnimationView.changeColorImage(ContextCompat.getDrawable(getContext(), image), Color.YELLOW );
//    }
//

//    public void hideSuit(){
//        if(hideSuit){
//            hideSuit =false;
//            pager.animate().alpha(1f).setDuration(1000).start();
//            image.animate().alpha(1f).setDuration(1000).start();
//
//        }else{
//            hideSuit =true;
//            pager.animate().alpha(0f).setDuration(1000).start();
//            image.animate().alpha(0f).setDuration(1000).start();
//        }
//
//        sendAnalystics("hideSuit", "hideSuit: " +hideSuit);
//
//    }

    private void sendAnalystics(String key, String value){
        //Analistic
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        mFirebaseAnalytics.logEvent("press", bundle);
    };

//    private void findCurrentPagerItems(){
//        Fragment myFragment = getParentFragmentManager().findFragmentByTag("myFragment" + pager.getCurrentItem());
//          animal = myFragment.getView().findViewById(R.id.animal);
//          suit_color = myFragment.getView().findViewById(R.id.suit_color);
//          suit = myFragment.getView().findViewById(R.id.suit);
//          moon = myFragment.getView().findViewById(R.id.moon);
//          nameNightlight = myFragment.getView().findViewById(R.id.nameNightlight);
//    }

    public void hideElements(){
        tab_l2.animate().alpha(0).setDuration(1000).start();
    }

    public void showElements(){
        tab_l2.animate().alpha(1f).setDuration(1000).start();
    }

}
