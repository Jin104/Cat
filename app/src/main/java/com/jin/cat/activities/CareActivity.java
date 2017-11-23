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

import static java.lang.Integer.valueOf;


public class CareActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"나이대별","일반","용품"};
    private int[] countryFlags = {R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        setTitle("관리");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = new Intent(this, CareDescActivity.class);
        mListView = (ListView) findViewById(R.id.health_listview);
        CareAdapter myAdapter = new CareAdapter(CareActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (valueOf(position)){

                    case 0:
                        intent.putExtra("contentId", "Age");
                        intent.putExtra("title","나이대별 고양이 관리");
                        break;

                    case 1:
                        intent.putExtra("contentId", "General");
                        intent.putExtra("title","일반 관리");
                        break;

                    case 2:
                        intent.putExtra("contentId", "Goods");
                        intent.putExtra("title","용품");
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
}
