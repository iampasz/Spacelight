package  com.appsforkids.pasz.spacelight.Adapters;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Interfaces.ChangeColors;
import com.appsforkids.pasz.spacelight.Objects.MenuButton;
import com.appsforkids.pasz.spacelight.R;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

   ArrayList<MenuButton> menuButtons;

    ChangeColors changeColors;
    int i = 0;

    public  void MyOnclick(ChangeColors changeColors){
        this.changeColors = changeColors;
    }

    public RecyclerViewAdapter(ArrayList menuButtons){
        this.menuButtons = menuButtons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.iconImage.setImageResource(menuButtons.get(position).getIconImege());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColors.onclick(menuButtons.get(position).getButton());
            }
        });

        holder.textMenu.setText(menuButtons.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return menuButtons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView textMenu;

        public MyViewHolder(View itemView) {
            super(itemView);
            iconImage = (ImageView) itemView.findViewById(R.id.play_item);
            textMenu = (TextView) itemView.findViewById(R.id.textMenu);
        }

    }
}
