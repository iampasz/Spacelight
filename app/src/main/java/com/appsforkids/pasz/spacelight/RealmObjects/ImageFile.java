package com.appsforkids.pasz.spacelight.RealmObjects;

import java.io.Serializable;

import io.realm.RealmObject;

public class ImageFile extends RealmObject implements Serializable {


    String imageInternetLink;

    public String getImage_internet_link() {
        return imageInternetLink;
    }

    public void setImage(String imageInternetLink) {
        this.imageInternetLink = imageInternetLink;
    }
}
