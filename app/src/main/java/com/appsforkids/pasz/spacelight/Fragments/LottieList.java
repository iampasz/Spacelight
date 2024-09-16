package com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.appsforkids.pasz.spacelight.Adapters.LottieAdapter;
import com.appsforkids.pasz.spacelight.Interfaces.GetActionFromAdapter;
import com.appsforkids.pasz.spacelight.Interfaces.GetJson;
import com.appsforkids.pasz.spacelight.MainActivity;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.ReadJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LottieList extends Fragment  {

    GridLayoutManager gm;

    ArrayList<com.appsforkids.pasz.spacelight.RealmObjects.LottieImage> lottieImages;
    RecyclerView rv_cards;
    MainFragment.ChoseItem choseItem;
    int height;
    ImageView close_button;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lottie_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Boolean internetConnection = ((MainActivity)getActivity()).hasConnection();

        LottieAnimationView no_internet = view.findViewById(R.id.no_internet2);

        if(internetConnection){
            no_internet.setVisibility(View.GONE);
        }else{
            no_internet.setVisibility(View.VISIBLE);
        }

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

        getArrayFromJson("https://koko-oko.com/images/lottie/lotties.json");

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment(-1);
            }
        });

    }

    public void setCallBack(MainFragment.ChoseItem choseItem){
        this.choseItem = choseItem;
    }

    public void closeFragment(int position){

        if(position==-1){

            getParentFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_out,
                            R.anim.fade_out)
                    .remove(this)
                    .commit();

        }else{

            LottieAnimationView lottieAnim =
                    (LottieAnimationView) rv_cards
                            .findViewHolderForAdapterPosition(position)
                            .itemView;
            ViewCompat.setTransitionName(lottieAnim, "lottie_view");
            String link = lottieImages.get(position).getImage_internet_link();

            getParentFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fade_in,
                            R.anim.fade_out)
                    .addSharedElement(lottieAnim, "hero_lottie")
                    .replace( R.id.container,  LottieImage.init(link))
                    .commit();
        }
        getParentFragmentManager().beginTransaction().remove(LottieList.this).commit();
        //Toast.makeText(getContext(), link+"link", Toast.LENGTH_SHORT).show();

        ImageView rocket = getActivity().findViewById(R.id.rocket);
        ImageView alien_bt = getActivity().findViewById(R.id.alien_bt);
        rocket.animate().alpha(1f).start();
        alien_bt.animate().alpha(1f).start();

    }

    private void getArrayFromJson(String url){
        ReadJson readJson = new ReadJson(new GetJson() {
            @Override
            public void getJson(String result) {

                lottieImages = new ArrayList<>();

                try {
                    String jsonText = result;

                    JSONObject jsonRoot = new JSONObject(jsonText);
                    JSONArray jsonArray = jsonRoot.getJSONArray("lottie");


                    for(int i = 0; jsonArray.length()>i; i++){
                        com.appsforkids.pasz.spacelight.RealmObjects.LottieImage lottieImage = new com.appsforkids.pasz.spacelight.RealmObjects.LottieImage();
                        lottieImage.setImage(jsonArray.getJSONObject(i).getString("internet_link"));
                        lottieImages.add(lottieImage);
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
        rv_cards.setAdapter(new LottieAdapter(lottieImages, height,   new GetActionFromAdapter() {
            @Override
            public void usePosition(int position) {
                closeFragment(position);
            }

        }));
    }



}
