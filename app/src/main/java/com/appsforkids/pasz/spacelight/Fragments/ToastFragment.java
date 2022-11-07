package  com.appsforkids.pasz.spacelight.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.spacelight.R;


public class ToastFragment extends Fragment {

    public static Fragment init(int image, String text){
        ToastFragment  toastFragment = new ToastFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        bundle.putInt("image", image);
        toastFragment.setArguments(bundle);
        return toastFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.toast_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text  = (String) getArguments().getString("text");
        int image  = (Integer) getArguments().getInt("image");
       ImageView imageView = (ImageView) view.findViewById(R.id.toastImage);
        TextView textView = (TextView) view.findViewById(R.id.toastText);
        imageView.setImageResource(image);
        textView.setText(text);
    }
}
