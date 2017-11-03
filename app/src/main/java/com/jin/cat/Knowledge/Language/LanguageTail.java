package com.jin.cat.Knowledge.Language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.jin.cat.R;

public class LanguageTail extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;

    String[] countryNames = {"상태1", "상태2"};
    String[] countryImfo = {"아아" , "으어어"};
    int[] countryFlags = {R.drawable.cat_three,
            R.drawable.cat_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setTitle("꼬리언어");

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter2 myAdapter = new LanguageAdapter2(LanguageTail.this, countryNames,countryImfo, countryFlags);

        mListView.setAdapter(myAdapter);
    }
}