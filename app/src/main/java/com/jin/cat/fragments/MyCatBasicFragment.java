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
public class MyCatBasicFragment extends Fragment {


    public MyCatBasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cat_basic, container, false);
    }

}
