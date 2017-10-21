package com.jin.cat.Knowledge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jin.cat.Knowledge.Language.LanguageActivity;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakha on 2017-10-21.
 */

public class KnowledgeActivity extends Fragment {

    public KnowledgeActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.knowledge, container, false);
        GridView gridview = (GridView)view.findViewById(R.id.gridview);

        final List<ItemObject> allItems = getAllItemObject();
        KnowledgeAdapter knowledgeAdapter = new KnowledgeAdapter(getActivity(), allItems);
        gridview.setAdapter(knowledgeAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        Intent intent = new Intent(getActivity(), LanguageActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    private List<ItemObject> getAllItemObject(){
        List<ItemObject> items = new ArrayList<>();
        items.add(new ItemObject(R.drawable.one,"행동언어", "고양이의 행동언어"));
        items.add(new ItemObject(R.drawable.two,"Someone like you", "Adele Adkins"));
        items.add(new ItemObject(R.drawable.three,"Ride", "Ciara"));
        items.add(new ItemObject(R.drawable.four,"Paparazzi", "Lady Gaga"));
        items.add(new ItemObject(R.drawable.five,"Forever", "Chris Brown"));
        items.add(new ItemObject(R.drawable.six,"Stay", "Rihanna"));
        items.add(new ItemObject(R.drawable.seven,"Marry me", "Jason Derulo"));
        items.add(new ItemObject(R.drawable.eight,"Waka Waka", "Shakira"));
        items.add(new ItemObject(R.drawable.nine,"Dark Horse", "Katy Perry"));
        items.add(new ItemObject(R.drawable.three,"Dip It Low", "Christina Milian"));
        items.add(new ItemObject(R.drawable.six,"Dip It Low", "Christina Milian"));
        items.add(new ItemObject(R.drawable.five,"Dip It Low", "Christina Milian"));
        return items;
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.knowledge, container, false);
//        return rootView;
//    }
}
