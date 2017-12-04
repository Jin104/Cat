package com.jin.cat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jin.cat.R;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FragmentMapActivity extends FragmentActivity {
    //private NMapView mMapView;

//    private static final String LOG_TAG = "NMapViewer";
//    private static final boolean DEBUG = false;
//
//    private NMapOverlayManager mOverlayManager;
//    private NMapViewrResourceProvider mMapViewrResourceProvider;


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
        setContentView(R.layout.map_framents);
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map_Fragment1 fragment1 = new Map_Fragment1();
                fragment1.setArguments(new Bundle());
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.fragmentHere, fragment1);
                fragmentTransaction.commit();
            }
        });
    }



//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.map);
//
//        init_map();
//        mMapView.setBuiltInZoomControls(true, null);
//
//        mMapViewrResourceProvider = new NMapViewrResourceProvider(this);
//        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewrResourceProvider);
//
//
//
//
//
//    }
//
//    public void init_map(){
//        MapFragment fragment1 = new MapFragment();
//        fragment1.setArguments(new Bundle());
//        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.add(R.id.fragmentHere, fragment1);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//



}