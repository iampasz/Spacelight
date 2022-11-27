package com.appsforkids.pasz.spacelight.Adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Interfaces.GetActionFromAdapter;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile;
import com.easyandroidanimations.library.ShakeAnimation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

   ArrayList<ImageBgFile> items;
    int size;
    GetActionFromAdapter getItemPosition;


    public ShopAdapter(ArrayList<ImageBgFile> items, int size,  GetActionFromAdapter getItemPosition){
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

        holder.image.setLayoutParams(new CardView.LayoutParams(size, (int) (size*1.5)));

        Picasso.get().load(items.get(holder.getAdapterPosition()).getImage_internet_link()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
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
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
