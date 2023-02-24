package com.appsforkids.pasz.spacelight.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.appsforkids.pasz.spacelight.R;
import com.easyandroidanimations.library.FadeInAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.no_button)
    FrameLayout no_button;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.yes_button)
    FrameLayout yes_button;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.frame_constraine)
    ConstraintLayout frame_constraine;
    TextView timer_text;

    public static TimerFragment init(){
        return new TimerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_fragment, container,  false);
        ButterKnife.bind(this, view);
        timer_text = (TextView) getActivity().findViewById(R.id.timer_text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new FadeInAnimation(view).setDuration(300).animate();
        new ScaleInAnimation(view).setDuration(500).animate();

        Spinner spinner_hours = (Spinner) view.findViewById(R.id.spinner_hours);
        Spinner spinner_minutes = (Spinner) view.findViewById(R.id.spinner_minutes);
        Integer[] items_hours = new Integer[]{0, 1, 2, 3, 4, 6};
        ArrayAdapter<Integer> adapter_hours = new ArrayAdapter<Integer>(getContext(),R.layout.simple_spinner_item, items_hours);
        adapter_hours.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner_hours.setAdapter(adapter_hours);
        spinner_hours.setSelection(0);

        Integer[] items_minutes = new Integer[]{0, 1, 5, 10, 20, 30, 40, 50};
        ArrayAdapter<Integer> adapter_minutes = new ArrayAdapter<Integer>(getContext(),R.layout.simple_spinner_item, items_minutes);
        adapter_minutes.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner_minutes.setAdapter(adapter_minutes);
        spinner_minutes.setSelection(0);


        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeThisFragment();
            }
        });

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer((int)spinner_hours.getSelectedItem(), (int) spinner_minutes.getSelectedItem());
                removeThisFragment();
            }
        });

    }

    private void removeThisFragment(){
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().remove(TimerFragment.this).commit();
    }

    public void startTimer(int hours, int minutes) {
        if (hours == 0 && minutes == 0) {
            timer_text.setVisibility(View.INVISIBLE);
        } else {
            timer_text.setVisibility(View.VISIBLE);
            int mySeconds = (((hours * 60 * 60) + (60 * minutes)) * 1000);
            CountDownTimer cdt = new CountDownTimer(mySeconds, 1000) {
                @SuppressLint("DefaultLocale")
                @Override
                public void onTick(long l) {
                    timer_text.setText(String.format("%02d:%02d:%02d", (l / 1000) / 3600, ((l / 1000) % 3600) / 60, (l / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    Log.i("FINISH", "App is OFF");
                    getActivity().finish();
                }
            };
            cdt.start();
        }

        //sendAnalystics("timer", "timer is: " + hours +" and " + minutes);

    }
}
