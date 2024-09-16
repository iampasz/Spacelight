package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.spacelight.R;
import com.squareup.picasso.Picasso;

public class Image extends Fragment {

    public static Image init(String link){
        Image fragment = new Image();
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


        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image);
        setSharedElementEnterTransition(transition);

        ImageView image = view.findViewById(R.id.image);
        ViewCompat.setTransitionName(image, "hero_image");
        Picasso.get().load(getArguments().getString("LINK")).into(image);

    }
}
