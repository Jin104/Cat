package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LanguageActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"귀","눈","수염","몸","꼬리"};
    private int[] countryFlags = {

            R.drawable.language_ear, R.drawable.language_eye, R.drawable.language_whiskers,
            R.drawable.language_body, R.drawable.language_tail};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setTitle("행동언어");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter myAdapter = new LanguageAdapter(LanguageActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

        final Intent intent = new Intent(LanguageActivity.this, LanguageListActivity.class);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        intent.putExtra("contentId", "귀");
                        break;
                    case 1:
                        intent.putExtra("contentId", "눈");
                        break;
                    case 2:
                        intent.putExtra("contentId", "수염");
                        break;
                    case 3:
                        intent.putExtra("contentId", "몸");
                        break;
                    case 4:
                        intent.putExtra("contentId", "꼬리");
                        break;
                    default:
                        break;

                }

                startActivity(intent);
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}