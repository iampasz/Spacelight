package  com.appsforkids.pasz.spacelight.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.spacelight.ColorController;
import com.appsforkids.pasz.spacelight.MyObjects;
import com.appsforkids.pasz.spacelight.Objects.Nightlighter;
import com.appsforkids.pasz.spacelight.R;
import com.appsforkids.pasz.spacelight.RealmObjects.MySettings;
//mport com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;


import java.util.Random;

import io.realm.Realm;


public class PagerFragment extends Fragment {

    Nightlighter myNightlighter;

    Realm realm;
    Context ctx;

     ImageView suitColorImg;
    TextView nameNightlight;


    public static PagerFragment newInt(Nightlighter nightlighter){

        PagerFragment pageFragment = new PagerFragment();

        Bundle arg = new Bundle();
        arg.putSerializable("nightlight", nightlighter);
        pageFragment.setArguments(arg);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myNightlighter = (Nightlighter) getArguments().getSerializable("nightlight");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctx = view.getContext();


         nameNightlight = (TextView) view.findViewById(R.id.nameNightlight);
        nameNightlight.setText(myNightlighter.getName());
        final ImageView moonImg = (ImageView) view.findViewById(R.id.moon);
        final ImageView suitImg = (ImageView) view.findViewById(R.id.suit) ;
         suitColorImg = (ImageView) view.findViewById(R.id.suit_color) ;
        final ImageView animalImg = (ImageView) view.findViewById(R.id.animal) ;

        moonImg.setImageResource(myNightlighter.getMoonImg());
        suitImg.setImageResource(myNightlighter.getSuitImg());
        suitColorImg.setImageResource(R.drawable.grad_blue_1);
        animalImg.setImageResource(myNightlighter.getAnimalImg());

      //  final ArrayList<Techniques> techniques = new ArrayList<>();
       //// techniques.add(Techniques.Bounce);
       // techniques.add(Techniques.BounceIn);
       // techniques.add(Techniques.FadeIn);
       //// techniques.add(Techniques.DropOut);
       // techniques.add(Techniques.Shake);
       // techniques.add(Techniques.Flash);
       // techniques.add(Techniques.SlideInLeft);
       // techniques.add(Techniques.Swing);
       // techniques.add(Techniques.FlipInY);


        nameNightlight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        moonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();


          //     int i =  random.nextInt(techniques.size());

               // YoYo.with(techniques.get(i))
               //         .duration(700)
               //         .playOn(upImg);

               // YoYo.with(techniques.get(i))
               //         .duration(700)
                 //       .playOn(underImg);
            }
        });

       //ColorController colorController = new  ColorController(suitColorImg);
      // colorController.startChangeColor();

        Realm.init(ctx);

        realm = Realm.getDefaultInstance();


        suitColorImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        hideSuit();
                        break;
                    case MotionEvent.ACTION_UP:
                        showSuit();
                        break;
                }

                return false;
            }
        });


    }

    public void hideSuit(){
        suitColorImg.setVisibility(View.INVISIBLE);
    }

    public void showSuit(){
        suitColorImg.setVisibility(View.VISIBLE);
    }





}
