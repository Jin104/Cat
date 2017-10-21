package com.jin.cat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rakha on 2017-10-21.
 */

public class Tab1Activity extends Fragment {

    //public Tab1Activity(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab1, container, false);
        return rootView;
    }
}
