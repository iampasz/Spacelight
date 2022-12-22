package com.appsforkids.pasz.spacelight.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Interfaces.SetOnClick;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.AudioFile;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    ArrayList <AudioFile> arrayList;

    SetOnClick setOnClick;

    ImageView oldView;

    public RVAdapter(ArrayList<AudioFile> arrayList, SetOnClick setOnClick){
        this.arrayList = arrayList;
        this.setOnClick = setOnClick;
    }


    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

       // holder.song_name.setText(arrayList.get(position).getName());
        //holder.author.setText(arrayList.get(position).getAutor());

        if(!arrayList.get(position).getStatus()){
            holder.download.setText("Download");
        }else{
            holder.download.setText("X");
        }

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.download.getText().equals("X")){


                }else {

                   // new DownloadFile(arrayList.get(position), holder.wrapper_list).execute(arrayList.get(position).getLink());

                    holder.progressBar.setVisibility(View.VISIBLE);

                    holder.progressBar.setProgress(20);


                }

            }
        });


        holder.wrapper_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(oldView!=null){
                    //oldView.setImageResource(R.drawable.play);
                }

               // holder.play_in_list.setImageResource(R.drawable.pause);
                setOnClick.click(position, holder.play_in_list);
                oldView = holder.play_in_list;


            }
        });

    }

    @Override
    public int getItemCount() {

     return arrayList.size();

    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView song_name;
        TextView author;
        TextView download;
        ConstraintLayout wrapper_list;
        ImageView play_in_list;
        ProgressBar progressBar;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

           // song_name = itemView.findViewById(R.id.song_name);
          //  author = itemView.findViewById(R.id.author);
           // download = itemView.findViewById(R.id.download);
           // wrapper_list = itemView.findViewById(R.id.wrapper_list);
          //  play_in_list = itemView.findViewById(R.id.play_in_list);
           // progressBar = itemView.findViewById(R.id.progressBar);

        }
    }


    public void updateData(ArrayList<AudioFile> viewModels) {
        notifyDataSetChanged();
    }


}
