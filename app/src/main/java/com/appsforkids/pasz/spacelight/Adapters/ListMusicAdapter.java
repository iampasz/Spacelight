package com.appsforkids.pasz.spacelight.Adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.appsforkids.pasz.spacelight.Interfaces.DownloadAndDelete;
import com.appsforkids.pasz.spacelight.Interfaces.PlayMyMusic;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;


import java.util.ArrayList;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ListMusicHolder> {

    PlayMyMusic playMyMusic;
    DownloadAndDelete downloadButton;
    ArrayList<AudioFile> audioFileAll;
    private String nameSong;

    public ListMusicAdapter(PlayMyMusic playMyMusic, DownloadAndDelete downloadButton, ArrayList<AudioFile> audioFileAll ){
        this.playMyMusic = playMyMusic;
        this.downloadButton = downloadButton;


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
            holder.download_bt.setImageResource(R.drawable.delete_vector_gradient);

        }


        holder.download_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //downloadButton.download(holder.getAdapterPosition());
                downloadButton.delete(holder.getAdapterPosition());




//                if(audioFileAll.get(holder.getAdapterPosition()).getStatus()){
//
//                    downloadButton.delete(holder.getAdapterPosition());
//
//                }else{
//
//                    downloadButton.download(holder.getAdapterPosition());
//
//                }

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
    private ImageView download_bt;
    private ConstraintLayout music_constrain;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.play_item);
            music_name = itemView.findViewById(R.id.music_name);
            music_author = itemView.findViewById(R.id.music_author);
            download_bt = itemView.findViewById(R.id.download_bt);
            music_constrain = itemView.findViewById(R.id.music_constrain);
        }
    }





    public void setPressedPosition(){
        nameSong="";
    }
}
