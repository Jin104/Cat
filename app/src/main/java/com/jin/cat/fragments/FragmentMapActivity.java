package com.jin.cat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.jin.cat.R;
import com.nhn.android.maps.NMapView;

public class FragmentMapActivity extends FragmentActivity {

    private NMapView mMapView;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.map_framents);
//
//        mMapView = (NMapView) findViewById(R.id.mapView);
//
//        // initialize map view
//        mMapView.setClickable(true);
//        mMapView.setEnabled(true);
//        mMapView.setFocusable(true);
//        mMapView.setFocusableInTouchMode(true);
//        mMapView.requestFocus();
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_map();



    }



    public void init_map(){
        MapFragment fragment1 = new MapFragment();
        fragment1.setArguments(new Bundle());
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentHere, fragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}