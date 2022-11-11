package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Adapters.ListImageAdapter;
import com.appsforkids.pasz.spacelight.Adapters.ListMusicAdapter;
import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadButton;
import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import java.util.ArrayList;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class BgListFragment extends Fragment {

    ImageView close_button;
    ListImageAdapter listImageAdapter;
    RecyclerView rv_melody;
    int currentMusicPosition = -1;
    ArrayList<AudioFile> arrayList;
    int clickId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Set View container and add buterknife library
        View view = inflater.inflate(R.layout.bg_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Realm.init(getContext());

        rv_melody = (RecyclerView) view.findViewById(R.id.rv_images);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_melody.setLayoutManager(llm);
        arrayList = getAudios();

        //Set current music position
        setSettings();


        PlayMyMusic playMyMusic = new PlayMyMusic() {
            @Override
            public void pressPosition(int position, Boolean play_status) {

                AudioFile audioFile = arrayList.get(position);

                Log.i("FIRST", audioFile.getResourceLink() + "we are");
                Log.i("FIRST", audioFile.getFileName() + "we are");
                Log.i("FIRST", audioFile.getLockalLink() + "we are");
                Log.i("FIRST", audioFile.getInternetLink() + "we are");
                Log.i("FIRST", audioFile.getNameSong() + "we are");
                Log.i("FIRST", audioFile.getAuthorSong() + "we are");
                if(audioFile.getResourceLink()!=0){
                    Log.i("FIRST", audioFile.getResourceLink() + "we are");
                    ((MainActivity)getActivity()).playMusic(audioFile.getResourceLink(), play_status);
                    Log.i("FIRST", "we are");

                }else{
                    if(audioFile.getLockalLink()!=null){
                        ((MainActivity)getActivity()).playLockalMusic(audioFile.getLockalLink(),play_status);
                    }else{
                        ((MainActivity)getActivity()).playLockalMusic(audioFile.getInternetLink(),play_status);
                    }
                }



                if(play_status){
                    currentMusicPosition = position;
                }else{
                    currentMusicPosition = -1;
                }


//                AudioFile audioFile = arrayList.get(position);
//
//                if (audioFile.getResourceLink() != 0) {
//                    Log.i("Playr", "Playe file:" + audioFile.getId());
//                    ((MainActivity) getActivity()).playMusic(audioFile.getId(), play_status);
//                } else {
//                    if (audioFile.getLockalLink() != null) {
//                        Log.i("Playr", "Playe file:" + audioFile.getLockalLink());
//
//                        ((MainActivity) getActivity()).playLockalMusic(audioFile.getLockalLink(), play_status);
//                    } else {
//                        if (audioFile.getInternetLink() != null) {
//                            Log.i("Playr", "Playe file:" + audioFile.getInternetLink());
//                            ((MainActivity) getActivity()).playLockalMusic(audioFile.getInternetLink(), play_status);
//                        } else {
//                            Log.i("Playr", "Sorry, but file is broken");
//                        }
//                    }
//                }
            }

        };

        DownloadButton downloadButton = new DownloadButton() {
            @Override
            public void download(int position) {
                Log.i("Test", position + " position");

                AudioFile audioFile = arrayList.get(position);

                clickId = audioFile.getId();

                Log.i("Test", audioFile.getInternetLink() + " audioFileAll.get(position).getInternetLink()");
                String file_name = audioFile.getFileName();
                new DownloadFileFromURL(getActivity(), file_name, new FileIsDownloaded() {
                    @Override
                    public void fileDownloaded(String path) {
                        Log.i("hello", "hello");
                        //Toast.makeText(getContext(), "hghggg", Toast.LENGTH_SHORT).show();
                        saveLink(clickId, path);
                        //Toast.makeText(getContext(), "sdsd", Toast.LENGTH_SHORT).show();
                        refreshList();
                    }
                }).execute(audioFile.getInternetLink());
            }
        };

        listImageAdapter = new ListImageAdapter(getContext(), playMyMusic, downloadButton);

        rv_melody.setAdapter(listImageAdapter);

        close_button = (ImageView) view.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(BgListFragment.this).commit();
            }
        });
    }

    //Play choosing melody in Main Activity
    public void playMusic(int musicId) {
        ((MainActivity) getActivity()).playMusic(musicId, true);

    }

    ;

    //Save current music position
    private void saveSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setCurrentMusicPosition(currentMusicPosition);
        realm.commitTransaction();
        Log.i("MelodyListFragment",  "saveSettings in MelodyListFragment. Current position was save. CurrentPosition is "+ currentMusicPosition);
    }

    //Get currentMusicPosition from saved settings
    public void setSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        currentMusicPosition = settings.getCurrentMusicPosition();
        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveSettings();
        Log.i("currentMusicPosition",  currentMusicPosition + "currentMusicPosition");
    }

    public ArrayList<AudioFile> getAudios() {

        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
//        //Перша мелодія яка яку не потрібно завантажувати з інтернету
//        AudioFile firstAudio = new AudioFile();
//        firstAudio.setResourseLink(R.raw.sound_file_3);
//        firstAudio.setNameSong("Stream");
//        firstAudio.setAuthorSong("Twarres");
//        firstAudio.setStatus(true);
//        firstAudio.setLockalLink("plug");
//
//        arrayList.add(firstAudio);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<AudioFile> realmResults = realm.where(AudioFile.class).sort("status", Sort.DESCENDING).findAll();
        realm.commitTransaction();
        arrayList.addAll(realmResults);
        return arrayList;
    }

    private String saveLink(int position, String lockalLink) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AudioFile needFile = realm.where(AudioFile.class).equalTo("id", position).findFirst();
        needFile.setLockalLink(lockalLink);

        needFile.setStatus(true);
        realm.commitTransaction();

        if (listImageAdapter != null) {
            listImageAdapter.notifyDataSetChanged();
        }

        return needFile.getInternetLink();
    }

    private void refreshList() {
        arrayList.clear();
        arrayList = getAudios();

    }
}