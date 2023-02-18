package com.appsforkids.pasz.spacelight.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.Interfaces.GetActionFromAdapter;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.LottieImage;

import java.util.ArrayList;

public class LottieAdapter extends RecyclerView.Adapter<LottieAdapter.ViewHolder>{

    ArrayList<LottieImage> items;
    int size;
    GetActionFromAdapter getItemPosition;


    public LottieAdapter(ArrayList<LottieImage> items, int size, GetActionFromAdapter getItemPosition){
        this.items = items;
        this.size = size;
        this.getItemPosition = getItemPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottie, parent, false);
        //renewPoint(true, view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardView.LayoutParams params = new CardView.LayoutParams(size, (int) (size*1.5));
        holder.lottie_nl.setLayoutParams(params);

        holder.lottie_nl.setAnimationFromUrl(items.get(holder.getAdapterPosition()).getImage_internet_link());



       // Picasso.get().load(items.get(holder.getAdapterPosition()).getImage_internet_link()).into(holder.image);
//
        holder.lottie_nl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemPosition.usePosition(items.get(holder.getAdapterPosition()).getImage_internet_link());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LottieAnimationView lottie_nl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lottie_nl = itemView.findViewById(R.id.lottie_nl);

        }
    }
}
