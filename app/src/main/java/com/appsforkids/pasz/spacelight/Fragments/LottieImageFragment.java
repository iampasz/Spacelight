package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.R;

public class LottieImageFragment extends Fragment {

    public static LottieImageFragment init(String link) {
        LottieImageFragment lottieImageFragment = new LottieImageFragment();
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

    }
}
