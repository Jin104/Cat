package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.adapter.LanguageAdapter;
import com.jin.cat.R;

public class LanguageActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"머리", "몸짓", "꼬리"};
    private int[] countryFlags = {
            R.drawable.cat_one,
            R.drawable.cat_two,
            R.drawable.cat_three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setTitle("행동언어");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter myAdapter = new LanguageAdapter(LanguageActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    startActivity(new Intent(LanguageActivity.this, LanguageFaceActivity.class));
                else if (position == 1)
                    startActivity(new Intent(LanguageActivity.this, LanguageBodyActivity.class));
                else if (position == 2)
                    startActivity(new Intent(LanguageActivity.this, LanguageTailActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}