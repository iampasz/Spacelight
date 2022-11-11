package com.appsforkids.pasz.spacelight.Adapters;//package com.appsforkids.pasz.lullabies;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.DownloadFileFromURL;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadButton;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ListMusicHolder> {

    PlayMyMusic playMyMusic;
    int pressedPosition = -1;
    int currentMusicPosition = -1;
    DownloadButton downloadButton;
    RealmResults<AudioFile> audioFileAll;

    public ListMusicAdapter(Context ctx, PlayMyMusic playMyMusic, DownloadButton downloadButton){
        this.playMyMusic = playMyMusic;
        this.downloadButton = downloadButton;

        audioFileAll = getAudios(ctx);

    };

    @NonNull
    @Override
    public ListMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_music, parent, false);
        return new ListMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicHolder holder, int position) {

            if((position%2)==0){
                holder.music_constrain.setBackgroundResource(R.drawable.music_item_bg);
            }
            else{
                if((position%1)==0){
                    holder.music_constrain.setBackgroundResource(R.drawable.music_item_bg);
                }
            }

        if(hasConnection(holder.music_constrain.getContext())==0  &&  audioFileAll.get(holder.getAdapterPosition()).getLockalLink() == null){
            holder.music_constrain.setVisibility(View.GONE);
        }

        //holder.play_item.setTag(UUID.randomUUID().toString());
        holder.music_name.setText(audioFileAll.get(holder.getAdapterPosition()).nameSong);
        holder.music_author.setText(audioFileAll.get(holder.getAdapterPosition()).authorSong);


        if(audioFileAll.get(holder.getAdapterPosition()).getStatus()){
            holder.download_frame.setVisibility(View.GONE);
        }

        holder.download_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // DownloadFileFromURL downloadFileFromURL =  new DownloadFileFromURL(holder.play_frame.ge)
                downloadButton.download(holder.getAdapterPosition());
            }
        });



        holder.music_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.i("Play", position +" it is i, and  listMusicHolder.getAdapterPosition() " +holder.getAdapterPosition());
                currentMusicPosition=-1;

                if(pressedPosition==holder.getAdapterPosition()){
                    //holder.play_item.setImageResource(R.drawable.bt_play);
                    playMyMusic.pressPosition(holder.getAdapterPosition(), false);
                    pressedPosition=-1;

                }else{
                    playMyMusic.pressPosition(holder.getAdapterPosition(), true);
                    //holder.play_item.setImageResource(R.drawable.bt_pause);
                    pressedPosition =  holder.getAdapterPosition();
                }
                notifyDataSetChanged();
            }
        });
        //Log.i("Play", pressedPosition +" presed and new " +holder.getAdapterPosition());
        if(holder.getAdapterPosition()==pressedPosition){
            holder.play_item.setImageResource(R.drawable.bt_pause);

        }else{
            holder.play_item.setImageResource(R.drawable.bt_play);
        }

        if(holder.getAdapterPosition()==currentMusicPosition && currentMusicPosition!=-1){
            holder.play_item.setImageResource(R.drawable.bt_pause);
        }

    }

    @Override
    public int getItemCount() {
        return audioFileAll.size();
    }

    public class ListMusicHolder extends RecyclerView.ViewHolder {

    private ImageView play_item;
    private TextView music_name;
    private TextView music_author;
    private FrameLayout play_frame;
    private FrameLayout download_frame;
    private ConstraintLayout music_constrain;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.play_item);
            music_name = itemView.findViewById(R.id.music_name);
            music_author = itemView.findViewById(R.id.music_author);
            play_frame = itemView.findViewById(R.id.play_frame);
            download_frame = itemView.findViewById(R.id.download_frame);
            music_constrain = itemView.findViewById(R.id.music_constrain);

        }
    }


    public  int hasConnection(final Context context)   {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return 3;
        }else{

        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return 2;
        }else{

        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return 1;
        }else{

        }
        return 0;
    }

    public RealmResults<AudioFile> getAudios(Context ctx){

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        audioFileAll = realm.where(AudioFile.class).sort("status", Sort.DESCENDING).findAll();
        pressedPosition = realm.where(MySettings.class).findFirst().getCurrentMusicPosition();
        realm.commitTransaction();
        return audioFileAll;
    }
}
