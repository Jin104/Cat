package com.jin.cat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jin.cat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCatTodayFragment extends Fragment {


    public MyCatTodayFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_cat_today, container, false);
    }

}
