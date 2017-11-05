package com.jin.cat;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jin.cat.Dictionary.DictionaryActivity;
import com.jin.cat.Knowledge.KnowledgeFragment;

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
                return new DictionaryActivity();
            case 1:
                return new KnowledgeFragment();
            case 2:
                return new KnowledgeFragment();
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
                return "DICTIONARY";
            case 1:
                return "KNOWLEDGE";
            case 2:
                return "MAP";
        }
        return null;
    }
}
