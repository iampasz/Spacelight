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
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.appsforkids.pasz.spacelight.Adapters.PagerAdapter;
import com.appsforkids.pasz.spacelight.AddToRealm;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;
import io.realm.RealmResults;

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

        TabLayout tabLayout =  view.findViewById(R.id.tab_layout);

        Boolean emptyDownloaded = true;


        Realm realm = Realm.getDefaultInstance();
        RealmResults<AudioFile> realmResults = realm
                .where(AudioFile.class)
                .equalTo("status",false)
                .findAll();

        RealmResults<AudioFile> realmResultstrue = realm
                .where(AudioFile.class)
                .equalTo("status",true)
                .findAll();

        if(realmResultstrue.size()==0){

        }else{
            emptyDownloaded=false;
            tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.downloaded)));
        }


        if(realmResults.size()==0){

        }else{

            tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.galery)));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager =  view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter (getChildFragmentManager(), tabLayout.getTabCount(), emptyDownloaded);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

           //  final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
             //   ft.remove(fragment);
               // ft.commit();


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
