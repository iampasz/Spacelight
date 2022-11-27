package com.appsforkids.pasz.spacelight.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Adapters.ListMusicAdapter;
import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadButton;
import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.MyObjects;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MelodyListFragment extends Fragment {

    ImageView close_button;
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
        View view = inflater.inflate(R.layout.my_music_list, container, false);
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
            Boolean answer = false;
            @Override
            public void pressPosition(int position, Boolean play_status) {

                nameSong = arrayList.get(position).getNameSong();
                Log.i("WHAT", "імя файлу яке було щойно натиснуте " + nameSong);

                if(play_status){
                    Log.i("WHAT", "зараз тут ПЛЕЙ " + play_status);

                }else{
                    Log.i("WHAT", "зараз тут СТОП " + play_status);
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
                                getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init("Інтернет вимкнено. Завантажте мелодію для програвання в фоновому режимі")).commit();

                                break;
                            case 1:
                                getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init("Рекомендуємо завантажити мелодію на мобільний пристрій")).commit();
                                pressPlay(position, play_status);
                                break;
                            case 2:
                                getParentFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.container,
                                                MessageFragment.init("Інтернет підключено до мобільної мережі. Рекомендуємо використовувати вайфай, або завантажити мелодію на пристрій. Продовжити використовувати мобільну мережу?", new DoThisAction() {
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
                                                        listMusicAdapter.notifyDataSetChanged();
                                                    }
                                                })).commit();
                                break;
                            case 3:
                                getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init("Рекомендуємо завантажити мелодію на мобільний пристрій")).commit();
                                pressPlay(position, play_status);
                                break;
                        }

                    }
                }

            }

            @Override
            public boolean getAnswer(){
                return answer;
            }

        };
        DownloadButton downloadButton = new DownloadButton() {
            @Override
            public void download(int position) {

                switch (hasConnection(getContext())) {
                    case 0:
                        getParentFragmentManager().beginTransaction().add(R.id.container, SimpleMessageFragment.init("Інтернет вимкнено. Для завантаження мелодії під'єднайтесь до інтернету")).commit();

                        break;
                    case 1:
                        pressDownload(position);
                        break;
                    case 2:

                        getParentFragmentManager()
                                .beginTransaction()
                                .add(R.id.container,
                                        MessageFragment.init("Інтернет підключено до мобільної мережі. Рекомендуємо використовувати вайфай. Продовжити використовувати мобільну мережу?", new DoThisAction() {
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
        };

        listMusicAdapter = new ListMusicAdapter(getContext(), playMyMusic, downloadButton);

        rv_melody.setAdapter(listMusicAdapter);

        close_button = (ImageView) view.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(MelodyListFragment.this).commit();
            }
        });
    }

    //Play choosing melody in Main Activity
    public void playMusic(int musicId) {
       // ((MainActivity) getActivity()).playMusic(musicId, true);

    }

    //Save current music position
    private void saveSettings() {
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setCurrentMusicPosition(currentMusicPosition);
        settings.setCurrentMusic(nameSong);
        //fileName = arrayList.get(position).getFileName();

        Log.i("WHAT", "ім'я файлу яке було зараз збережене в налаштуваннях " + nameSong);
        realm.commitTransaction();
    }

    //Get currentMusicPosition from saved settings
    public void setSettings() {
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        nameSong = settings.getCurrentMusic();
        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveSettings();
    }

    public ArrayList<AudioFile> getAudios() {

        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }

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

        if (listMusicAdapter != null) {
            //listMusicAdapter.notifyDataSetChanged();
        }
        return needFile.getInternetLink();
    }

    private void refreshList() {
        listMusicAdapter.setPressedPosition();
        arrayList.clear();
        arrayList = getAudios();
        listMusicAdapter.notifyDataSetChanged();
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
            ((MainActivity) getActivity()).playMusic(audioFile.getResourceLink(), play_status);

        } else {
            if (audioFile.getLockalLink() != null) {
                ((MainActivity) getActivity()).playLockalMusic(audioFile.getLockalLink(), play_status);
            } else {
                  ((MainActivity) getActivity()).playLockalMusic(audioFile.getInternetLink(), play_status);
            }
        }

        if (play_status) {
            currentMusicPosition = position;
        } else {
            currentMusicPosition = -1;
        }
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
        }).execute(audioFile.getInternetLink());
    }
}