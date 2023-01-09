package com.appsforkids.pasz.spacelight;

import android.content.Context;
import android.graphics.Color;

import com.appsforkids.pasz.spacelight.Objects.MenuButton;
import com.appsforkids.pasz.spacelight.Objects.Nightlighter;

import java.util.ArrayList;

public class MyObjects {

    Context ctx;

    private static final int SOUNDS = 1;
    private static final int BGCOLOR = 2;
    private static final int NLCOLOR = 3;
    private static final int NIGHTLIGHT = 4;
    private static final int TIMER = 5;
    private static final int BRIGHTS = 6;

    private static final int EMPTY_SOUND = 7;
    private static final int FIRST_SOUND = 8;
    private static final int SECOND_SOUND = 9;

    private static final int RED_COLOR = 10;
    private static final int WHITE_COLOR = 11;
    private static final int BLACK_COLOR = 12;
    private static final int ORANGE_COLOR = 13;
    private static final int YELLOW_COLOR = 14;
    private static final int GREEN_COLOR = 15;
    private static final int CANYAN_COLOR = 16;
    private static final int BLUE_COLOR = 17;
    private static final int MOOD_COLOR = 18;


    public MyObjects(Context ctx){
        this.ctx = ctx;
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

        int[] gradientArray =  {
                R.drawable.grad_blue_1,
                R.drawable.grad_blue_6,
                R.drawable.grad_pink_17,
        };
        return gradientArray;

    };

    public int[] getBackground(){
        int[] backgrounds = {
                R.drawable.bg_002,
                R.drawable.bg_016,
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


    public ArrayList<Nightlighter> getNightlighters(){
        ArrayList<Nightlighter> list = new ArrayList<>();
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_hippo, R.string.hippo));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_cat, R.string.cat));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_koala, R.string.koala));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_cow, R.string.cow));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_monkey, R.string.monkey));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_panda, R.string.panda));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_racoon, R.string.raccoon));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_pig, R.string.pig));
        list.add(new Nightlighter(R.drawable.moon, R.drawable.suit, R.drawable.suit_color, R.drawable.an_bear, R.string.bear));
        return list;
    }

    public ArrayList<MenuButton> getMenuButtons(){
        final ArrayList<MenuButton> menuButtons = new ArrayList<>();
        menuButtons.add(new MenuButton( R.drawable.home_vector_gradient, R.string.home, 0));
        menuButtons.add(new MenuButton( R.drawable.paint_vector_gradient, R.string.paint, 1));
        menuButtons.add(new MenuButton( R.drawable.image_vector_gradient, R.string.image, 2));
        menuButtons.add(new MenuButton( R.drawable.time_vector_gradient, R.string.time, 3));
        menuButtons.add(new MenuButton( R.drawable.anim_vector_gradient, R.string.anim, 4));
        menuButtons.add(new MenuButton( R.drawable.light_vector_gradient, R.string.brightness, 5));
        menuButtons.add(new MenuButton( R.drawable.suit_vector_gradient, R.string.suit_color, 6));
        menuButtons.add(new MenuButton( R.drawable.question_vector_gradient, R.string.politica, 7));

        return menuButtons;
    }
}
