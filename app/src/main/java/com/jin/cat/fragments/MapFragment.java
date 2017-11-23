package com.jin.cat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jin.cat.R;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapView;


public class MapFragment extends Fragment {

    private NMapContext mMapContext;

    private NMapView mapView;

    private static final String CLIENT_ID = "h0jOnpEEU05opv5JOxw9";// 애플리케이션 클라이언트 아이디 값

    private NMapView findMapView(View v) {

        if (!(v instanceof ViewGroup)) {
            return null;
        }

        ViewGroup vg = (ViewGroup)v;
        if (vg instanceof NMapView) {
            return (NMapView)vg;
        }

        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);
            if (!(child instanceof ViewGroup)) {
                continue;
            }

            NMapView mapView = findMapView(child);
            if (mapView != null) {
                return mapView;
            }
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
       // throw new IllegalArgumentException("onCreateView should be implemented in the subclass of NMapFragment.");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        NMapView mapView = findMapView(super.getView());
//        if (mapView == null) {
//            throw new IllegalArgumentException("NMapFragment dose not have an instance of NMapView.");
//        }
//
//        //NMapView mapView = (NMapView)getView().findViewById(R.id.mapView);
//
//        // mapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
//        mMapContext.setupMapView(mapView);

        mapView = (NMapView)getView().findViewById(R.id.mapView);

        mapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정

        mMapContext.setupMapView(mapView);
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }
    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }
}
