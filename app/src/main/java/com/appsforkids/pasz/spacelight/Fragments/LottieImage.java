package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.R;

public class LottieImage extends Fragment {

    public static LottieImage init(String link) {
        LottieImage lottieImageFragment = new LottieImage();
        Bundle bundle = new Bundle();
        bundle.putString("LINK", link);
        lottieImageFragment.setArguments(bundle);
        return lottieImageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_lottie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LottieAnimationView lottie = view.findViewById(R.id.lottie_nl);
        lottie.setAnimationFromUrl(getArguments().getString("LINK"));
        lottie.playAnimation();

        Transition transition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image);
        setSharedElementEnterTransition(transition);

        ViewCompat.setTransitionName(lottie, "hero_lottie");

    }
}
