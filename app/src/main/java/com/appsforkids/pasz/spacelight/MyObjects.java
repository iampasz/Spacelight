package com.appsforkids.pasz.spacelight;

import android.content.Context;

import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
//import com.appsforkids.pasz.spacelight.RealmObjects.Nightlight;
import com.appsforkids.pasz.spacelight.RealmObjects.ShopItems;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyObjects {

    Context ctx;

    public MyObjects(Context ctx){
        this.ctx = ctx;
    }

//    public RealmResults<Nightlight> getNightlightersArrayList(){
//            Realm.init(ctx);
//            Realm realm = Realm.getDefaultInstance();
//            realm.beginTransaction();
//            RealmResults nightlightsResult = realm.where(Nightlight.class).findAll();
//            realm.commitTransaction();
//            return nightlightsResult;
//        }

    public RealmResults<AudioFile> getMelodyArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults melodyResult = realm.where(AudioFile.class).findAll();
        realm.commitTransaction();
        return melodyResult;
    }




    public String[]   getColors(){
            return ctx.getResources().getStringArray(R.array.myColors);
        }


    public int[]   getAnimationImage(){

        int[] randomImage =  {
                R.drawable.sm_1,
                R.drawable.sm_2,
                R.drawable.sm_3,
                R.drawable.sm_4,
                R.drawable.sm_6,
                R.drawable.sm_7,
                R.drawable.sm_8,
                R.drawable.sm_9,
                R.drawable.sm_10,
                R.drawable.sm_11,
                R.drawable.sm_12,
                R.drawable.sm_13,
                //R.drawable.sm_14,
               // R.drawable.sm_15,
                //R.drawable.sm_16,
                //R.drawable.sm_17,
                //R.drawable.sm_18,
               // R.drawable.sm_19,
                //R.drawable.sm_20,
               // R.drawable.sm_21,
               // R.drawable.sm_22,
               // R.drawable.sm_23,
               // R.drawable.sm_24,
               // R.drawable.sm_25,
               // R.drawable.sm_26
        };

        return randomImage;

    };

    public int[] getGradient(){

        int[] gradient = {R.drawable.gr_blue,
                R.drawable.gr_blue_0,
                R.drawable.gr_blue_1,
                R.drawable.gr_blue_2,
                R.drawable.gr_blue_3,
                R.drawable.gr_blue_4,
                R.drawable.gr_blue_5,
                R.drawable.gr_blue_6,
                R.drawable.gr_blue_7,
                R.drawable.gr_dark,
                R.drawable.gr_dark_2,
                R.drawable.gr_green,
                R.drawable.gr_green_0,
                R.drawable.gr_green_2,
                R.drawable.gr_green_3,
                R.drawable.gr_orange_1,
                R.drawable.gr_pink_1,
                R.drawable.gr_pink_2,
                R.drawable.gr_pink_22,
                R.drawable.gr_pink_3,
                R.drawable.gr_pink_4,
                R.drawable.gr_pink_5,
                R.drawable.gr_pink_6,
                R.drawable.gr_red,
                R.drawable.gr_white,
                R.drawable.gr_white_2,
                R.drawable.gr_yellow,
        };

        return gradient;
    }

    public int[]   getGradientArray(){

        int[] gradientArray =  {R.drawable.grad_blue_1,
              //  R.drawable.grad_blue_2,
               // R.drawable.grad_blue_3,
               // R.drawable.grad_blue_4,
              //  R.drawable.grad_blue_5,
                R.drawable.grad_blue_6,
               // R.drawable.grad_blue_7,
              //  R.drawable.grad_blue_8,
              //  R.drawable.grad_blue_9,
               // R.drawable.grad_blue_10,
               // R.drawable.grad_blue_11,
              //  R.drawable.grad_blue_12,
               // R.drawable.grad_pink_1,
               // R.drawable.grad_pink_2,
              //  R.drawable.grad_pink_3,
               // R.drawable.grad_pink_4,
               // R.drawable.grad_pink_5,
               // R.drawable.grad_pink_6,
               // R.drawable.grad_pink_7,
               // R.drawable.grad_pink_8,
               // R.drawable.grad_pink_9,
               // R.drawable.grad_pink_10,
               // R.drawable.grad_pink_11,
               // R.drawable.grad_pink_12,
               // R.drawable.grad_pink_13,
               // R.drawable.grad_pink_14,
                //R.drawable.grad_pink_15,
                //R.drawable.grad_pink_16,
                R.drawable.grad_pink_17,
                //R.drawable.grad_pink_18,
                //R.drawable.grad_pink_19,
               // R.drawable.grad_pink_20,
        };
        return gradientArray;

    };

    public int[] getBackground(){
        int[] backgrounds = {
               //R.drawable.bg_001,
                R.drawable.bg_002,
               // R.drawable.bg_003,
               // R.drawable.bg_004,
                //R.drawable.bg_005,
               // R.drawable.bg_006,
               // R.drawable.bg_007,
                //R.drawable.bg_008,
                //R.drawable.bg_009,
                //R.drawable.bg_010,
                //R.drawable.bg_011,
                //R.drawable.bg_012,
                //R.drawable.bg_013,
                //R.drawable.bg_014,
                //R.drawable.bg_015,
                R.drawable.bg_016,
                //R.drawable.bg_017,
                //R.drawable.bg_019,
                //R.drawable.bg_020,
                //R.drawable.bg_021,
                //R.drawable.bg_022,
                //R.drawable.bg_023,
                //R.drawable.bg_024,
                //R.drawable.bg_025,
                //R.drawable.bg_026,
                //R.drawable.bg_027,
                //R.drawable.bg_028

        };
        return backgrounds;
    }

    public float[] getBrights(){

        float[] brights =  {
                0.0f,
                0.3f,
                0.5f,
                0.8f,
                1f
        };

        return brights;
    }

//    public ArrayList<Card> getCards(){
//        ArrayList<Card> cardArrayList = new ArrayList<>();
//        Card card1 = new Card();
//        card1.setImage(R.drawable.an_bear);
//        card1.setName("an_bear");
//        Card card2 = new Card();
//        card2.setImage(R.drawable.an_dear);
//        card2.setName("an_dear");
//        Card card3 = new Card();
//        card3.setImage(R.drawable.an_rabbit);
//        card3.setName("an_rabbit");
//        Card card4 = new Card();
//        card4.setImage(R.drawable.an_elephan);
//        card4.setName("an_elephan");
//        Card card5 = new Card();
//        card5.setImage(R.drawable.an_fox);
//        card5.setName("an_fox");
//        Card card6 = new Card();
//        card6.setImage(R.drawable.an_cheetah);
//        card6.setName("an_cheetah");
//        Card card7 = new Card();
//        card7.setImage(R.drawable.an_squirrel);
//        card7.setName("an_squirrel");
//        Card card8 = new Card();
//        card8.setImage(R.drawable.an_hedgehog);
//        card8.setName("an_hedgehog");
//        cardArrayList.add(card1);
//        cardArrayList.add(card1);
//        cardArrayList.add(card2);
//        cardArrayList.add(card2);
//        cardArrayList.add(card3);
//        cardArrayList.add(card3);
//        cardArrayList.add(card4);
//        cardArrayList.add(card4);
//        cardArrayList.add(card5);
//        cardArrayList.add(card5);
//        cardArrayList.add(card6);
//        cardArrayList.add(card6);
//        cardArrayList.add(card7);
//        cardArrayList.add(card7);
//        cardArrayList.add(card8);
//        cardArrayList.add(card8);
//
//        Collections.shuffle(cardArrayList);
//        return cardArrayList;
//    }


}
