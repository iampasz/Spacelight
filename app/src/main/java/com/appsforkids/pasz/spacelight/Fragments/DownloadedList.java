package com.appsforkids.pasz.spacelight.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Adapters.ListMusicAdapter;
import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadAndDelete;
import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class DownloadedList extends Fragment {

    ListMusicAdapter listMusicAdapter;
    RecyclerView rv_melody;
    int currentMusicPosition = -1;
    ArrayList<AudioFile> arrayList;
    int clickId;
    String nameSong ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Set View container and add buterknife library
        View view = inflater.inflate(R.layout.play_l, container, false);
        ButterKnife.bind(this, view);
        arrayList = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_melody = (RecyclerView) view.findViewById(R.id.rv_images);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_melody.setLayoutManager(llm);
        getAudios();

        //Set current music position
        setSettings();

        PlayMyMusic playMyMusic = new PlayMyMusic() {
            Boolean answer = false;
            @Override
            public void pressPosition(int position, Boolean play_status) {

                nameSong = arrayList.get(position).getNameSong();

                if(play_status){

                }else{
                    nameSong ="";
                }

                if(arrayList.get(position).getLockalLink()!=null){
                    pressPlay(position, play_status);
                }else{

                    if(!play_status){
                        pressPlay(position, play_status);
                    }else{

                        switch (hasConnection(getContext())) {
                            case 0:
                                getParentFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_1)))
                                        .commit();
                                break;
                            case 1:
                                getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_2))).commit();
                                pressPlay(position, play_status);
                                break;
                            case 2:
                                getParentFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.container,
                                                MessageFragment.init(getResources().getString(R.string.message_1), new DoThisAction() {
                                                    @Override
                                                    public void doThis() {
                                                        pressPlay(position, play_status);
                                                    }
                                                    @Override
                                                    public void doThis(int hours, int minutes) {
                                                    }
                                                    @Override
                                                    public  void doThat() {
                                                        listMusicAdapter.setPressedPosition();
                                                        //listMusicAdapter.notifyDataSetChanged();
                                                    }
                                                })).commit();
                                break;
                            case 3:
                                getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_2))).commit();
                                pressPlay(position, play_status);
                                break;
                        }
                    }
                }
            }

        };
        DownloadAndDelete downloadButton = new DownloadAndDelete()
        {
            @Override
            public void download(int position) {

                switch (hasConnection(getContext())) {
                    case 0:
                        getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init(getResources().getString(R.string.message_1))).commit();
                        break;
                    case 1:
                        pressDownload(position);
                        break;
                    case 2:
                        getParentFragmentManager()
                                .beginTransaction()
                                .add(R.id.container,
                                        MessageFragment.init(getResources().getString(R.string.message_3), new DoThisAction() {
                                            @Override
                                            public void doThis() {
                                                listMusicAdapter.setPressedPosition();
                                                pressDownload(position);
                                            }
                                            @Override
                                            public void doThis(int hours, int minutes) {
                                            }
                                            @Override
                                            public void doThat() {
                                            }
                                        })).commit();
                        break;
                    case 3:
                        pressDownload(position);
                        break;
                }
            }

            @Override
            public void delete(int position) {
                getParentFragmentManager()
                        .beginTransaction()
                        .add(R.id.audio_content, MessageFragment
                                .init(getString(R.string.are_sure), new DoThisAction() {
                    @Override
                    public void doThis() {
                        AudioFile audioFile = arrayList.get(position);
                        File file = new File(audioFile.getLockalLink());
                        boolean file_status = file.delete();
                        deleteRealmFile(audioFile.getLockalLink());
                        arrayList.clear();
                        getAudios();
                        listMusicAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void doThis(int hours, int minutes) {

                    }

                    @Override
                    public void doThat() {

                    }
                }))
                        .commit();
                //good link with correct answer how to add or delete adapter's items
                //https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data




            }
        };

        listMusicAdapter = new ListMusicAdapter(playMyMusic, downloadButton, arrayList);
        rv_melody.setAdapter(listMusicAdapter);


    }

    //Save current music position
    private void saveSettings() {

        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setCurrentMusicPosition(currentMusicPosition);
        settings.setCurrentMusic(nameSong);

        realm.commitTransaction();
    }

    //Get currentMusicPosition from saved settings
    public void setSettings() {
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        if(settings!=null){
            nameSong = settings.getCurrentMusic();
        }

        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveSettings();
    }

    private String saveLink(int position, String lockalLink) {
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        AudioFile needFile = realm.where(AudioFile.class).equalTo("id", position).findFirst();
        needFile.setLockalLink(lockalLink);
        needFile.setStatus(true);
        realm.commitTransaction();

        return needFile.getInternetLink();
    }

    private void refreshList() {
        listMusicAdapter.setPressedPosition();
        arrayList.clear();
        getAudios();
        //listMusicAdapter.notifyDataSetChanged();
    }

    public int hasConnection(final Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 3;
        } else {

        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 2;
        } else {

        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return 1;
        } else {

        }
        return 0;
    }

    private void pressPlay(int position, Boolean play_status){


        AudioFile audioFile = arrayList.get(position);


        if (audioFile.getResourceLink() != 0) {
            ((MainActivity) getActivity()).playMusic(audioFile.getResourceLink(),audioFile.nameSong, audioFile.authorSong,  play_status);
        } else {
            if (audioFile.getLockalLink() != null) {
                ((MainActivity) getActivity()).playLockalMusic(audioFile.getLockalLink(), audioFile.nameSong+" \n "+audioFile.getAuthorSong(), play_status);
            } else {
               // ((MainActivity) getActivity()).playInternetMusic(audioFile, play_status);
            }



        }
//
//        if (play_status) {
//            currentMusicPosition = position;
//        } else {
//            currentMusicPosition = -1;
//        }
    }

    private void pressDownload(int position){

        AudioFile audioFile = arrayList.get(position);
        clickId = audioFile.getId();

        String file_name = audioFile.getFileName();
        new DownloadFileFromURL(getActivity(), file_name, new FileIsDownloaded() {
            @Override
            public void fileDownloaded(String path) {
                saveLink(clickId, path);
                refreshList();
            }
        }, true).execute(audioFile.getInternetLink());
    }

    private void getAudios(){
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        RealmResults<AudioFile> realmResults;
        //RealmResults<AudioFile> realmResults = realm.where(AudioFile.class).findAll();

        //Перша мелодія яка яку не потрібно завантажувати з інтернету
        AudioFile firstAudio = new AudioFile();
        firstAudio.setResourseLink(R.raw.sound_file_3);
        firstAudio.setNameSong("Stream");
        firstAudio.setAuthorSong("Twarres");
        firstAudio.setStatus(true);
        firstAudio.setLockalLink("plug");

        arrayList.add(firstAudio);

//        switch (hasConnection(getContext())){
//            case 0:
//                realmResults = realm.where(AudioFile.class).equalTo("status", true).findAll();
//
//                break;
//            default:
//                realmResults = realm.where(AudioFile.class).sort("status", Sort.DESCENDING).findAll();
//        }

        realmResults = realm.where(AudioFile.class).equalTo("status", true).findAll();

        for(int i = 0; realmResults.size()>i; i++){

            AudioFile audioFile = realmResults.get(i);
            Log.i("ara", audioFile.getLockalLink()+" jj");

        }

        // realm.commitTransaction();//??

        arrayList.addAll(realmResults);
    }

    private void deleteRealmFile(String lockalLink) {
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        realm.where(AudioFile.class).equalTo("lockalLink", lockalLink).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

}