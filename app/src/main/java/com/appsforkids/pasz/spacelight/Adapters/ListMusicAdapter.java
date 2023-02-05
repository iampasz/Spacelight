package com.appsforkids.pasz.spacelight.Adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
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
    DownloadButton downloadButton;
    ArrayList<AudioFile> audioFileAll;
    private String nameSong;

    public ListMusicAdapter( PlayMyMusic playMyMusic, DownloadButton downloadButton, ArrayList<AudioFile> audioFileAll, String nameSong){
        this.playMyMusic = playMyMusic;
        this.downloadButton = downloadButton;
        this.nameSong = nameSong;

        this.audioFileAll = audioFileAll;
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

        holder.music_name.setText(audioFileAll.get(holder.getAdapterPosition()).nameSong);
        holder.music_author.setText(audioFileAll.get(holder.getAdapterPosition()).authorSong);

        if(audioFileAll.get(holder.getAdapterPosition()).getStatus()){
            holder.download_frame.setVisibility(View.GONE);
        }

        holder.download_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadButton.download(holder.getAdapterPosition());
            }
        });

        holder.music_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.play_item.setImageResource(R.drawable.pause_vector_gradient);

                if(audioFileAll.get(holder.getAdapterPosition()).getNameSong().equals(nameSong)){
                    holder.play_item.setImageResource(R.drawable.play_vector_gradient);
                    playMyMusic.pressPosition(holder.getAdapterPosition(), false);
                    nameSong="";
                }else{
                    playMyMusic.pressPosition(holder.getAdapterPosition(), true);
                    holder.play_item.setImageResource(R.drawable.pause_vector_gradient);
                    nameSong = audioFileAll.get(holder.getAdapterPosition()).getNameSong();
                }
                // notifyDataSetChanged();
            }
        });

        if(audioFileAll.get(holder.getAdapterPosition()).getNameSong().equals(nameSong)){
            holder.play_item.setImageResource(R.drawable.pause_vector_gradient);

        }else{
            holder.play_item.setImageResource(R.drawable.play_vector_gradient);
        }

        if(audioFileAll.get(holder.getAdapterPosition()).getNameSong().equals(nameSong) && nameSong!=""){
            holder.play_item.setImageResource(R.drawable.pause_vector_gradient);
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
    private FrameLayout download_frame;
    private ConstraintLayout music_constrain;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.play_item);
            music_name = itemView.findViewById(R.id.music_name);
            music_author = itemView.findViewById(R.id.music_author);
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



    public void setPressedPosition(){
        nameSong="";
    }
}
