package com.jin.cat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jin.cat.Knowledge.Language.LanguageAdapter2;
import com.jin.cat.R;

public class LanguageTailActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"상태1", "상태2"};
    private String[] countryImfo = {"아아" , "으어어"};
    private int[] countryFlags = {R.drawable.cat_three,
            R.drawable.cat_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setTitle("꼬리언어");

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter2 myAdapter = new LanguageAdapter2(LanguageTailActivity.this, countryNames,countryImfo, countryFlags);

        mListView.setAdapter(myAdapter);
    }
}