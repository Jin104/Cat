package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;
import com.jin.cat.adapter.CareAdapter;

/**
 * Created by inhye on 2017-12-06.
 */

public class CareActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"나이","일반"};
    private int[] countryFlags = {R.drawable.cat, R.drawable.cat2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        setTitle("관리");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.health_listview);
        //HealthAdapter myAdapter = new HealthAdapter(CareActivity.this, countryNames, countryFlags);
        CareAdapter myAdapter = new CareAdapter(CareActivity.this, countryNames, countryFlags);
        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent intent1 = new Intent(CareActivity.this, AgeCareActivity.class);
                        intent1.putExtra("contentId", "Age");
                        intent1.putExtra("title","Age");
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(CareActivity.this, GeneralCareActivity.class);
                        intent2.putExtra("contentId", "General");
                        intent2.putExtra("title","General");
                        startActivity(intent2);
                        break;
                }
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