package com.jin.cat.Dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakha on 2017-10-21.
 */

public class DictionaryActivity extends Fragment {

    public DictionaryActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView3);

        final List<DictionaryItem> allItems = getAllDictionaryAdapter();
        DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(getActivity(), allItems);
        listView.setAdapter(dictionaryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        Intent intent = new Intent(getActivity(), DictionaryLonghair.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), DictionaryMiddlehair.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), DictionaryShorthair.class);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    private List<DictionaryItem> getAllDictionaryAdapter(){
        List<DictionaryItem> items = new ArrayList<>();
        items.add(new DictionaryItem(R.drawable.one,"장모종"));
        items.add(new DictionaryItem(R.drawable.two,"중모종"));
        items.add(new DictionaryItem(R.drawable.three,"단모종"));
        return items;
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_knowledge, container, false);
//        return rootView;
//    }
}
