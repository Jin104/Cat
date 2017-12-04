package com.jin.cat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jin.cat.R;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPathData;
import com.nhn.android.maps.overlay.NMapPathLineStyle;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MapFragment extends Fragment {
    //defulat field for used NMap
    private NMapContext mMapContext = null;
    private NMapView mMapView = null;
    private NMapController mMapController = null;

    //Used for GPS Location
    private NGeoPoint currentPoint = null;
    private NMapLocationManager mMapLocationManager = null;

    //Used for drawing Overlay
    private NMapViewerResourceProvider mMapViewrResourceProvider = null;
    private NMapOverlayManager mOverlayManager = null;

    private static final String LOG_TAG = "NMapViewer";
    private static final String CLIENT_ID = "h0jOnpEEU05opv5JOxw9";// 애플리케이션 클라이언트 아이디 값

//    private NMapView findMapView(View v) {
//
//        if (!(v instanceof ViewGroup)) {
//            return null;
//        }
//
//        ViewGroup vg = (ViewGroup)v;
//        if (vg instanceof NMapView) {
//            return (NMapView)vg;
//        }
//
//        for (int i = 0; i < vg.getChildCount(); i++) {
//
//            View child = vg.getChildAt(i);
//            if (!(child instanceof ViewGroup)) {
//                continue;
//            }
//
//            NMapView mapView = findMapView(child);
//            if (mapView != null) {
//                return mapView;
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment1, container, false);
//       // throw new IllegalArgumentException("onCreateView should be implemented in the subclass of NMapFragment.");
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//       mMapContext =  new NMapContext(super.getActivity());
//        mMapContext.onCreate();
//
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        NMapView mapView = findMapView(super.getView());
////        if (mapView == null) {
////            throw new IllegalArgumentException("NMapFragment dose not have an instance of NMapView.");
////        }
////
////        //NMapView mapView = (NMapView)getView().findViewById(R.id.mapView);
////
////        // mapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
////        mMapContext.setupMapView(mapView);
//        mMapView = (NMapView)getView().findViewById(R.id.mapView);
//
//        mMapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
//
//        mMapContext.setupMapView(mMapView);
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//        mMapContext.onStart();
//        //mMapView.setOnMapStateChangeListener(this);
//
//    }
//
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapContext.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapContext.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        mMapContext.onStop();
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        mMapContext.onDestroy();
//        super.onDestroy();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();

        mMapLocationManager = new NMapLocationManager(getActivity());
        currentPoint = new NGeoPoint(126.922522, 35.183415);
        //mMapLocationManager.setOnLocationChangeListener(new NMapLocationManager.OnLocationChangeListener() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMapView = (NMapView)getView().findViewById(R.id.mapView);
        mMapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정

        mMapContext.setupMapView(mMapView);

        mMapView.setClickable(true);

        mMapController = mMapView.getMapController();

        if(mMapController != null) {
            NGeoPoint geoPoint = getGPSLocation();
            mMapController.setMapCenter(new NGeoPoint(126.922522, 35.183415), 11);
        }

        mMapView.setBuiltInZoomControls(true, null);

        mMapViewrResourceProvider = new NMapViewerResourceProvider(getContext());
        mOverlayManager = new NMapOverlayManager(getContext(), mMapView, mMapViewrResourceProvider);

        drawOverlay();
    }

    private void drawOverlay() {
        int markerId = NMapPOIflagType.PIN;

        NMapPOIdata  poiData = new NMapPOIdata(2, mMapViewrResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(currentPoint.getLongitude(), currentPoint.getLatitude(), "My Home", markerId, 0);

        int index = 0;
        List<NGeoPoint> locations = getRandLocation(currentPoint);
        for (NGeoPoint location : locations) {
            poiData.addPOIitem(location.getLongitude(), location.getLatitude(), index + "", markerId, 0);
            index++;
        }
        poiData.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        //drawPath(locations);
        poiDataOverlay.showAllPOIdata(0);
    }

    private  void drawPath(List<NGeoPoint> locations) {
        NMapPathData pathData = new NMapPathData(locations.size());
        for(NGeoPoint location : locations) {
            pathData.addPathPoint(location.getLongitude(), location.getLatitude(), NMapPathLineStyle.TYPE_DASH);
        }
        pathData.endPathData();

        NMapPathDataOverlay pathDataOverlay = mOverlayManager.createPathDataOverlay(pathData);
    }

    private NGeoPoint getGPSLocation() {
        return mMapLocationManager.getMyLocation();
    }

    private List<NGeoPoint> getRandLocation(NGeoPoint stdPoint) {
        List<NGeoPoint> locations = new ArrayList<NGeoPoint>();

        Random random = new Random();

        for(int i = 0; i < 10; i++) {
            NGeoPoint point = new NGeoPoint(stdPoint.getLongitude() + (random.nextDouble() * 0.02 - 0.01) ,
                    stdPoint.getLatitude() + (random.nextDouble() * 0.02 - 0.01));
            locations.add(point);
        }

        return locations;
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
