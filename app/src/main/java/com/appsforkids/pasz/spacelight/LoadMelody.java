package com.appsforkids.pasz.spacelight;

import android.app.Activity;
import android.util.Log;

import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.Interfaces.GetJson;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import io.realm.Realm;

public class LoadMelody {

    Activity activity;

    public  LoadMelody(Activity activity) {
        this.activity = activity;
        addJsonToRealm();
    }

    public void addJsonToRealm () {
        ReadJson readJson = new ReadJson(new GetJson() {
            @Override
            public void getJson(String result) {
                try {
                    String jsonText = result;
                    JSONObject jsonRoot = new JSONObject(jsonText);
                    JSONArray jsonArray = jsonRoot.getJSONArray("music");
                    //Toast.makeText(context, jsonArray.length()+" ", Toast.LENGTH_SHORT).show();
                    for (int i = 0; jsonArray.length() > i; i++) {
                        AudioFile audioFile = new AudioFile();
                        audioFile.setNameSong(jsonArray.getJSONObject(i).getString("name"));
                        audioFile.setFileName(jsonArray.getJSONObject(i).getString("file_name"));
                        audioFile.setAuthorSong(jsonArray.getJSONObject(i).getString("author"));
                        audioFile.setInternetLink(jsonArray.getJSONObject(i).getString("internet_link"));
                        audioFile.setStatus(jsonArray.getJSONObject(i).getBoolean("status"));

                        Log.i("LoadMelody", audioFile.getFileName()+" is file_name");
                        Log.i("LoadMelody", audioFile.getInternetLink()+" is internet link");

                        if(chekAudios(audioFile.getFileName())){
                            Log.i("LoadMelody", "This file is loaded");
                        }else{
                            addA(audioFile);
                            Log.i("LoadMelody"," add to audio list");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        readJson.execute("https://koko-oko.com/audio/music_load.json");


    }

    public Boolean chekAudios(String file_name){
        assert Realm.getDefaultConfiguration() != null;
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        realm.beginTransaction();
        AudioFile obj = realm.where(AudioFile.class).containsValue("fileName", file_name).findFirst();
        realm.commitTransaction();

        if(obj!=null){
            return true;
        }

        return false;
    }

    private void saveLink(AudioFile audioFile) {
            assert Realm.getDefaultConfiguration() != null;
            Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
            realm.beginTransaction();
            realm.copyToRealm(audioFile);
            Log.i("LoadMelody"," fwe add new melody");
            realm.commitTransaction();
    }

    public void addA(AudioFile audioFile){
            new DownloadFileFromURL(activity, audioFile.getFileName(), new FileIsDownloaded() {
                @Override
                public void fileDownloaded(String path) {
                    Log.i("LoadMelody",path + " aragon");
                    audioFile.setLockalLink(path);
                    audioFile.setStatus(true);
                    saveLink(audioFile);
                    Log.i("LoadMelody"," file is loaded ");
                }
            }, false).execute(audioFile.getInternetLink());
    }
}
