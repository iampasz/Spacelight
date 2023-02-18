package com.appsforkids.pasz.spacelight.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.appsforkids.pasz.spacelight.Fragments.GalleryList;
import com.appsforkids.pasz.spacelight.Fragments.DownloadedList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;



    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

            DownloadedList playList = new DownloadedList();
            return playList;
            case 1:
                GalleryList musicGallery = new GalleryList();
                return musicGallery;
//            case 2:
//                ImageGallery imageGalery = new ImageGallery();
//                return imageGalery;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}