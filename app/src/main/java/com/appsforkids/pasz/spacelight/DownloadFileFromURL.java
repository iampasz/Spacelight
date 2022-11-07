package com.appsforkids.pasz.spacelight;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.appsforkids.pasz.spacelight.Interfaces.FileIsDownloaded;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import io.realm.Realm;

import static android.content.Context.MODE_PRIVATE;

/**
 * Background Async Task to download file
 * */public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    int id;
    String file_name;
    Activity activity;

    ProgressDialog mProgressDialog;


    FileIsDownloaded fileIsDownloaded;

    public DownloadFileFromURL(Activity activity, String file_name, FileIsDownloaded fileIsDownloaded) {
        this.file_name = file_name;
        this.activity = activity;
        this.fileIsDownloaded = fileIsDownloaded;

        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Downloaded: "+file_name);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);




    }

    /**
     * Before starting background thread Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       //activity.showDialog(progress_bar_type);
        mProgressDialog.show();
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(@NonNull String... f_url) {

  //      AudioFile audioFile = getAudio(id);
//        String file_name = audioFile.getFileName();

        Log.i("CHEK", f_url[0]+ " Лінк з інтернету");


        int count;
        try {
            Log.i("CHEK", 1+ " connectionу");
            URL url = new URL(f_url[0]);
            Log.i("CHEKK", " connectionу1");
            URLConnection connection = url.openConnection();


            Log.i("CHEKK", " connectionу2");

            Log.i("CHEKK", connection+" connectionу2");

            connection.connect();
            Log.i("CHEKK", " connectionу3");
            Log.i("CHEK", connection+ " connectionу");
            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = connection.getContentLength();

            Log.i("CHEK", lenghtOfFile+ " Розмір файлу");

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);


            // Output stream
//            OutputStream output = new FileOutputStream(Environment
//                    .getExternalStorageDirectory().toString()
//                    + "/Music/"+file_name+".mp3");


           // OutputStream output = new FileOutputStream( "/"+file_name);
            // Output stream

            OutputStream output = activity.openFileOutput(file_name, MODE_PRIVATE);




            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }


            // flushing output

           // fileIsDownloaded.fileDownloaded();

            output.flush();

            // closing streams
            output.close();
            input.close();

            Log.i("CHEK",  "Файл завантажено");



            //String lockalLink = activity.getFilesDir().getAbsoluteFile()+"/"+file_name+".mp3";

           // saveLink(audioFile, lockalLink);

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            Log.i("CHEK",  "Файл не завантажується. Можливо проблеми з лінком або з інтернет налаштуваннями");
        }

        return null;
    }

    protected void onProgressUpdate(String... progress) {
        // if we get here, length is known, now set indeterminate to false
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgress(Integer.parseInt(progress[0]));
    }

    @Override
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after the file was downloaded
       // activity.dismissDialog(progress_bar_type);
        mProgressDialog.dismiss();

        fileIsDownloaded.fileDownloaded(activity.getFilesDir().getAbsoluteFile()+"/"+file_name+"");

       // Log.i("CHEK", "dismiss the dialog after the file was downloaded");

    }



    private AudioFile getAudio(int id){
        Realm realm = Realm.getDefaultInstance();
        AudioFile audioFile = realm.where(AudioFile.class).equalTo("id", id).findFirst();


        return audioFile;
    }

    private String saveLink(AudioFile audioFile, String lockalLink){


        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        audioFile.setLockalLink(lockalLink);
        audioFile.setStatus(true);
        realm.commitTransaction();

        return audioFile.getInternetLink();
    }

}
