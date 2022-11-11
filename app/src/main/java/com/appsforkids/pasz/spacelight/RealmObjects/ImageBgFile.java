package com.appsforkids.pasz.spacelight.RealmObjects;

import io.realm.RealmObject;

public class ImageBgFile extends RealmObject {


    String imageInternetLink;

    public String getImage_internet_link() {
        return imageInternetLink;
    }

    public void setImage(String imageInternetLink) {
        this.imageInternetLink = imageInternetLink;
    }
}
