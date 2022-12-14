package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.spacelight.Adapters.ShopAdapter;
import com.appsforkids.pasz.spacelight.Interfaces.GetActionFromAdapter;
import com.appsforkids.pasz.spacelight.Interfaces.GetJson;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.ReadJson;
import com.appsforkids.pasz.spacelight.RealmObjects.ImageBgFile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackgroundFragment extends Fragment  {

    GridLayoutManager gm;

    ArrayList<ImageBgFile> imagesArray;
    RecyclerView rv_cards;
     MainFragment.ChoseItem choseItem;
    int height;
    ImageView close_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.shop_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int spanCount = 3;

        gm = new GridLayoutManager(getContext(),spanCount, RecyclerView.VERTICAL, false);

         rv_cards = view.findViewById(R.id.rv_cards);
        close_button = view.findViewById(R.id.close_button);
        rv_cards.setLayoutManager(gm);

        //MyObjects myObjects = new MyObjects(getContext());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         height = displayMetrics.widthPixels;
        height = height/spanCount;

       getArrayFromJson("https://koko-oko.com/images/bg_image_sl/images.json");


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment("");
            }
        });



//        ImageView close_button = view.findViewById(R.id.close_button);
//        close_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                closeFragment(0);
//
//            }
//        });

    }


    public void setCallBack(MainFragment.ChoseItem choseItem){
        this.choseItem = choseItem;
    }

    public void closeFragment(String link){

        if(link.equals("")){

        }else{

            choseItem.setImage(link);
        }
        getParentFragmentManager().beginTransaction().remove(BackgroundFragment.this).commit();

    }

    private void getArrayFromJson(String url){



        ReadJson readJson = new ReadJson(new GetJson() {
            @Override
            public void getJson(String result) {

                 imagesArray = new ArrayList<>();

                try {
                    String jsonText = result;

                    JSONObject jsonRoot = new JSONObject(jsonText);
                    JSONArray jsonArray = jsonRoot.getJSONArray("image");


                    for(int i = 0; jsonArray.length()>i; i++){
                        ImageBgFile imageBgFile = new ImageBgFile();
                        imageBgFile.setImage(jsonArray.getJSONObject(i).getString("internet_link"));
                        imagesArray.add(imageBgFile);
                    }

                    showImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        readJson.execute(url);

    }

    private void showImages(){
        rv_cards.setAdapter(new ShopAdapter(imagesArray, height,   new GetActionFromAdapter() {
            @Override
            public void usePosition(String link) {
                closeFragment(link);
            }

        }));
    }

}
