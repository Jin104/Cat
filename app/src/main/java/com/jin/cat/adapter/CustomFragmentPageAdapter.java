package com.jin.cat.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jin.cat.fragments.Blank1Fragment;
import com.jin.cat.fragments.BlankFragment;
import com.jin.cat.fragments.CatFragment;
import com.jin.cat.fragments.DictionaryFragment;
import com.jin.cat.fragments.KnowledgeFragment;
import com.jin.cat.fragments.MapFragment;
import com.jin.cat.fragments.MyCatFragment;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CatFragment();
            case 1:
                return new DictionaryFragment();
            case 2:
                return new KnowledgeFragment();
//            case 3:
//                return new MapFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "MY CAT";
            case 1:
                return "DICTIONARY";
            case 2:
                return "KNOWLEDGE";
//            case 3:
//                return "MAP";
        }
        return null;
    }
}
