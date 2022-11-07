package com.appsforkids.pasz.spacelight.Objects;

import java.io.Serializable;

public class Nightlighter implements Serializable {

    int moonImg;
    int suitImg;
    int suitColorImg;
    int animalImg;
    int name;

    public Nightlighter(int moonImg, int suitImg, int suitColorImg, int animalImg, int name){
        this.moonImg = moonImg;
        this.suitImg = suitImg;
        this.suitColorImg = suitColorImg;
        this.animalImg = animalImg;
        this.name = name;

    }

    public int getName() {
        return name;
    }

    public int getAnimalImg() {
        return animalImg;
    }

    public int getMoonImg() {
        return moonImg;
    }

    public int getSuitColorImg() {
        return suitColorImg;
    }

    public int getSuitImg() {
        return suitImg;
    }
}
