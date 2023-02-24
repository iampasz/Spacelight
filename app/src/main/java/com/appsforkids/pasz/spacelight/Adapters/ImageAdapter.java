package com.appsforkids.pasz.spacelight.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Interfaces.GetActionFromAdapter;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.ImageFile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

   ArrayList<ImageFile> items;
    int size;
    GetActionFromAdapter getItemPosition;


    public ImageAdapter(ArrayList<ImageFile> items, int size, GetActionFromAdapter getItemPosition){
        this.items = items;
        this.size = size;
        this.getItemPosition = getItemPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bg, parent, false);
        //renewPoint(true, view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardView.LayoutParams params = new CardView.LayoutParams(size, (int) (size*1.5));
        holder.image.setLayoutParams(params);

        Picasso.get().load(items.get(holder.getAdapterPosition()).getImage_internet_link()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemPosition.usePosition(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            ViewCompat.setTransitionName(image, "item_image");
        }
    }
}
