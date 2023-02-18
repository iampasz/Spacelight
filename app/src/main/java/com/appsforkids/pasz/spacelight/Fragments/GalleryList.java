package com.appsforkids.pasz.spacelight.Fragments;

import android.annotation.SuppressLint;
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

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.Adapters.ListMusicAdapter;
import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.DoThisAction;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadAndDelete;
import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.Interfaces.GetJson;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.ReadJson;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindInt;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class GalleryList extends Fragment {


    RecyclerView rv_melody;
    int currentMusicPosition = -1;
    String nameSong ="";
    ArrayList<AudioFile> arrayList;

    ListMusicAdapter listMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Set View container and add buterknife library
        View view = inflater.inflate(R.layout.download_ml, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_melody = (RecyclerView) view.findViewById(R.id.rv_images);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_melody.setLayoutManager(llm);

        arrayList =  new ArrayList<>();
        arrayList.addAll(getAudios());

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
                                        .add(R.id.audio_content, SimpleMessageFragment.init(getResources().getString(R.string.message_1)))
                                        .commit();
                                break;
                            case 1:
                                getParentFragmentManager().beginTransaction().add(R.id.audio_content, SimpleMessageFragment.init(getResources().getString(R.string.message_2))).commit();
                                pressPlay(position, play_status);
                                break;
                            case 2:
                                getParentFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.audio_content,
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
                                                        //listMusicAdapter.setPressedPosition();
                                                        // listMusicAdapter.notifyDataSetChanged();
                                                    }
                                                })).commit();
                                break;
                            case 3:
                                getParentFragmentManager().beginTransaction().add(R.id.audio_content, SimpleMessageFragment.init(getResources().getString(R.string.message_2))).commit();
                                pressPlay(position, play_status);
                                break;
                        }
                    }
                }
            }

        };
        DownloadAndDelete downloadButton = new DownloadAndDelete() {
            @Override
            public void download(int position) {



            }
            @Override
            public void delete(int position) {

                pressDownload(position);
                arrayList.remove(position);
                listMusicAdapter.notifyItemRemoved(position);
                //listMusicAdapter.notifyItemRemoved(position);
                // arrayList.clear();
                // Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();

            }
        };



        listMusicAdapter = new ListMusicAdapter(
                playMyMusic,
                downloadButton,
                arrayList);

        rv_melody.setAdapter(listMusicAdapter);

        Boolean internetConnection = ((MainActivity)getActivity()).hasConnection();
        LottieAnimationView no_internet = view.findViewById(R.id.no_internet_dl);

        if(internetConnection){
            no_internet.setVisibility(View.GONE);
        }else{
            no_internet.setVisibility(View.VISIBLE);
        }
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
       // saveSettings();
    }

    private void saveLink(String file_name, String lockalLink) {

        Log.i("SAVED_LINK",file_name+" position");
        Log.i("SAVED_LINK",lockalLink+" localLink");
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        AudioFile needFile = realm
                .where(AudioFile.class)
                .equalTo("fileName", file_name)
                .findFirst();

        needFile.setLockalLink(lockalLink);
        needFile.setStatus(true);

        realm.commitTransaction();

        Log.i("SAVED_LINK",needFile.getFileName()+" file");

        //arrayList.clear();
       // rv_melody.notify();
       // listMusicAdapter.notifyDataSetChanged();
    }

    private void refreshList() {
        //listMusicAdapter.setPressedPosition();
        //arrayList.clear();
        //getAudios();
       // listMusicAdapter.notifyDataSetChanged();
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
            ((MainActivity) getActivity()).playMusic(audioFile.getResourceLink(),audioFile.getNameSong(), audioFile.authorSong,  play_status);
        } else {
            if (audioFile.getLockalLink() != null) {
                ((MainActivity) getActivity()).playLockalMusic(audioFile, play_status);
            } else {
                ((MainActivity) getActivity()).playInternetMusic(audioFile, play_status);
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
        //clickId = audioFile.getFileName();
        String file_name = audioFile.getFileName();
        DownloadFileFromURL dff = new DownloadFileFromURL(getActivity(), file_name, new FileIsDownloaded() {
            @Override
            public void fileDownloaded(String path) {
                Log.i("TRY_REFRESH", " 111");
                saveLink(file_name, path);
            }
        }, true);
        dff.execute(audioFile.getInternetLink());

        arrayList.clear();
        arrayList.addAll(getAudios());

        //listMusicAdapter.notify();

        //listMusicAdapter.notifyItemRemoved(position);


    }

    private RealmResults<AudioFile> getAudios(){
        RealmResults<AudioFile> realmResults;
        //RealmResults<AudioFile> realmResults = realm.where(AudioFile.class).findAll();
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realmResults = realm.where(AudioFile.class).equalTo("status", false).findAll();
        // realm.commitTransaction();//??

        return realmResults;
    }



}