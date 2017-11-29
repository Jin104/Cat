package com.jin.cat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jin.cat.R;

public class MyCatTodayFragment extends Fragment {


    private Spinner mSpinner;

    public MyCatTodayFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cat_today, container, false);

        final String catId = getArguments().getString("catId");
        mSpinner = (Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.type, android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapter);


        return view;
    }

}
