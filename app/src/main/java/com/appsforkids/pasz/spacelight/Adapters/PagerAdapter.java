package com.appsforkids.pasz.spacelight.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.appsforkids.pasz.spacelight.Fragments.GalleryList;
import com.appsforkids.pasz.spacelight.Fragments.DownloadedList;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;

import io.realm.Realm;
import io.realm.RealmResults;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    Boolean emptyDownload;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Boolean emptyDownload) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.emptyDownload = emptyDownload;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if(emptyDownload){
                    GalleryList galleryList = new GalleryList();
                    return galleryList;
                }else{
                    DownloadedList downloadedList = new DownloadedList();
                    return downloadedList;
                }
            case 1:
                GalleryList musicGallery = new GalleryList();
                return musicGallery;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}