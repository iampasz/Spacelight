package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.spacelight.R;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    public static ImageFragment init(String link){
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("LINK", link);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_bg, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView image = view.findViewById(R.id.image);
        Picasso.get().load(getArguments().getString("LINK")).into(image);

    }
}
