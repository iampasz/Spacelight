package com.appsforkids.pasz.spacelight.RealmObjects;

import java.io.Serializable;

import io.realm.RealmObject;

public class ShopItems extends RealmObject implements Serializable {

    public int cost;
    public int image_item;
    public boolean status;
    public String itemsName;
    public int big_image;

    public void setBigImage(int big_image) {
        this.big_image = big_image;
    }

    public int setBigImage() {
        return big_image;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setImage_item(int image_item) {
        this.image_item = image_item;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public int getImage_item() {
        return image_item;
    }

    public boolean getStatus() {
        return status;
    }
}
