package com.appsforkids.pasz.spacelight;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.appsforkids.pasz.spacelight.Interfaces.GetJson;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
import com.appsforkids.pasz.spacelight.RealmObjects.ShopItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.realm.Realm;

class AddToRealm {

    Context ctx;
    Number maxId = 0;

    AddToRealm(Context ctx) {

        this.ctx = ctx;
    }



    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public void getJsonFromURL(){

        ReadJson readJson = new ReadJson(new GetJson() {
            @Override
            public void getJson(String result) {

                ArrayList<AudioFile> musicItemArrayList = new ArrayList<>();

                try {

                    String jsonText = result;

                    JSONObject jsonRoot = new JSONObject(jsonText);
                    JSONArray jsonArray = jsonRoot.getJSONArray("music");

                    //Toast.makeText(context, jsonArray.length()+" ", Toast.LENGTH_SHORT).show();
                    for(int i = 0; jsonArray.length()>i; i++){

                        AudioFile audioFile = new AudioFile();

                        audioFile.setId(jsonArray.getJSONObject(i).getInt("id"));
                        audioFile.setNameSong(jsonArray.getJSONObject(i).getString("name"));
                        audioFile.setFileName(jsonArray.getJSONObject(i).getString("file_name"));
                        audioFile.setAuthorSong(jsonArray.getJSONObject(i).getString("author"));
                        audioFile.setInternetLink(jsonArray.getJSONObject(i).getString("internet_link"));
                        audioFile.setStatus( jsonArray.getJSONObject(i).getBoolean("status"));

                        //Toast.makeText(context, jsonArray.getJSONObject(0).getString("name")+"55", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, musicItem.getName()+"55", Toast.LENGTH_SHORT).show();

                        musicItemArrayList.add(audioFile);

                    }


                    Realm.init(ctx);
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    if(realm.where(AudioFile.class).findFirst()!=null){
                        maxId =  realm.where(AudioFile.class).max("id");
                    }

                    for(int i = 0; i<musicItemArrayList.size(); i++){

                        if(musicItemArrayList.get(i).getId()<=maxId.intValue()){

                        }else{
                            realm.insert(musicItemArrayList.get(i));
                        }
                    }

                    realm.commitTransaction();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        readJson.execute("https://koko-oko.com/audio/music.json");
    }

}
