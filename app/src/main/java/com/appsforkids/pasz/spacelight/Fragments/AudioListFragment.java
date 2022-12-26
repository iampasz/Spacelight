package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.appsforkids.pasz.spacelight.Adapters.PagerAdapter;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.google.android.material.tabs.TabLayout;

public class AudioListFragment extends Fragment {


    ImageView close_button;


    public static AudioListFragment init(){
        AudioListFragment audioListFragment = new AudioListFragment();

        Bundle bundle = new Bundle();

        audioListFragment.setArguments(bundle);

        return audioListFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.audiolist, container, false);

        close_button = (ImageView) view.findViewById(R.id.close_button);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        Toolbar toolbar =  view.findViewById(R.id.toolbar);
     //   getActivity().setActionBar(toolbar);
        TabLayout tabLayout =  view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.downloaded)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.galery)));
       // tabLayout.addTab(tabLayout.newTab().setText("Image gallery"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager =  view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).updateAudioList();
                getParentFragmentManager().beginTransaction().remove(AudioListFragment.this).commit();

            }
        });
    }
}
