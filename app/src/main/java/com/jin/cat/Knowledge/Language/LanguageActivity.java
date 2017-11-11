package com.jin.cat.Knowledge.Language;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;

public class LanguageActivity extends AppCompatActivity {

    ListView mListView;

    Intent mfrist;
    Intent msecond;
    Intent mThird;

    String[] countryNames = {"머리", "몸짓", "꼬리"};
    int[] countryFlags = {
            R.drawable.cat_one,
            R.drawable.cat_two,
            R.drawable.cat_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        mfrist = new Intent(LanguageActivity.this, LanguageFace.class);
        msecond = new Intent(LanguageActivity.this, LanguageBody.class);
        mThird = new Intent(LanguageActivity.this, LanguageTail.class);

        setTitle("Language");

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter myAdapter = new LanguageAdapter(LanguageActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    startActivity(mfrist);
                else if (position == 1)
                    startActivity(msecond);
                else if (position == 2)
                    startActivity(mThird);
            }
        });
    }
}