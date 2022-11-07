package com.appsforkids.pasz.spacelight.Fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.R;

import java.io.File;

public class BackgroundsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.backgrounds_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        DownloadFileFromURL downloadFileFromURL = new DownloadFileFromURL(getActivity(), "image.jpg", new FileIsDownloaded() {
//            @Override
//            public void fileDownloaded(String path) {
//
//            }
//        });

       // downloadFileFromURL.execute("https://koko-oko.com/images/bg_image_sl/bg_001.jpg");


    }


}
