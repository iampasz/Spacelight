package  com.appsforkids.pasz.spacelight.Adapters;


import androidx.fragment.app.FragmentManager;


import com.appsforkids.pasz.spacelight.Fragments.PagerFragment;
import com.appsforkids.pasz.spacelight.Objects.Nightlighter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPagerAdapter extends MyFragmentStatePagerAdapter {

    FragmentManager mFragMan;
    ArrayList<Nightlighter> list;


    HashMap<Integer, PagerFragment> mPageReferenceMap;
    public MyPagerAdapter(FragmentManager fm, ArrayList<Nightlighter> list) {
        super(fm);
        mFragMan = fm;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public PagerFragment getItem(int position) {

        position = position % list.size();

        return PagerFragment.newInt(list.get(position));
    }


    public PagerFragment getFragment(int key) {
        return mPageReferenceMap.get(key);
    }
}