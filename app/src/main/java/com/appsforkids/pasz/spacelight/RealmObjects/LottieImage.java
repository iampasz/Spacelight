package com.appsforkids.pasz.spacelight.RealmObjects;

import java.io.Serializable;

import io.realm.RealmObject;

public class LottieImage extends RealmObject implements Serializable {


    String lottieInternetLink;

    public String getImage_internet_link() {
        return lottieInternetLink;
    }

    public void setImage(String imageInternetLink) {
        this.lottieInternetLink = imageInternetLink;
    }
}
