package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;
import com.jin.cat.adapter.HealthAdapter;


/**
 * Created by sunmoon on 2017-10-28.
 */

public class HealthActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"예방접종","병"};
    private int[] countryFlags = {R.drawable.cat, R.drawable.cat2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        setTitle("건강");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.health_listview);
        HealthAdapter myAdapter = new HealthAdapter(HealthActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent intent1 = new Intent(HealthActivity.this, VaccinationActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(HealthActivity.this, DiseaseActivity.class);
                        intent2.putExtra("contentId", "병");
                        intent2.putExtra("title","병");
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