package com.appsforkids.pasz.spacelight.Adapters;//package com.appsforkids.pasz.lullabies;

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
import com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ListMusicHolder> {

    PlayMyMusic playMyMusic;
    int pressedPosition = -1;
    int currentMusicPosition = -1;
    DownloadButton downloadButton;
    RealmResults<ImageBgFile> imageBgFileAll;

    public ListImageAdapter(Context ctx, PlayMyMusic playMyMusic, DownloadButton downloadButton){
        this.playMyMusic = playMyMusic;
        this.downloadButton = downloadButton;

        imageBgFileAll = getImages(ctx);

    };

    @NonNull
    @Override
    public ListMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_bg, parent, false);
        return new ListMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicHolder holder, int position) {

       // holder.image.setImageResource(imageBgFileAll.get(holder.getAdapterPosition()).getImage());

        //Log.i("Play", pressedPosition +" presed and new " +holder.getAdapterPosition());
        if(holder.getAdapterPosition()==pressedPosition){
           // holder.play_item.setImageResource(R.drawable.bt_pause);

        }else{
            //holder.play_item.setImageResource(R.drawable.bt_play);
        }

        if(holder.getAdapterPosition()==currentMusicPosition && currentMusicPosition!=-1){
            //holder.play_item.setImageResource(R.drawable.bt_pause);
        }

    }

    @Override
    public int getItemCount() {
        return imageBgFileAll.size();
    }

    public class ListMusicHolder extends RecyclerView.ViewHolder {

    private ImageView image;


        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);


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

    public RealmResults<ImageBgFile> getImages(Context ctx){

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        imageBgFileAll = realm.where(ImageBgFile.class).sort("status", Sort.DESCENDING).findAll();
        pressedPosition = realm.where(MySettings.class).findFirst().getCurrentMusicPosition();
        realm.commitTransaction();
        return imageBgFileAll;
    }
}
