package com.jin.cat.Dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.jin.cat.Knowledge.ItemObject;
import com.jin.cat.Knowledge.KnowledgeAdapter;
import com.jin.cat.Knowledge.Language.LanguageActivity;
import com.jin.cat.Knowledge.Language.LanguageAdapter;
import com.jin.cat.Knowledge.Language.LanguageBody;
import com.jin.cat.Knowledge.Language.LanguageFace;
import com.jin.cat.Knowledge.Language.LanguageTail;
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
        items.add(new DictionaryItem(R.drawable.long_hair,"장모종"));
        items.add(new DictionaryItem(R.drawable.mid_hair,"중모종"));
        items.add(new DictionaryItem(R.drawable.short_hair,"단모종"));
        return items;
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.knowledge, container, false);
//        return rootView;
//    }
}
