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
import com.jin.cat.adapter.CatList1Acdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DictionaryActivity extends AppCompatActivity {

    private Intent intent;

    private ListView mlistView;
    private Intent mFirst;

    private String[] countryNames = {"장모종", "중모종", "단모종"};
    private int[] countryFlags = {R.drawable.dictionary_cat3, R.drawable.dictionary_cat2, R.drawable.dictionary_cat1};
    private String[] count={"17 cats", "5 cats", "32 cats"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        setTitle("고양이 사전");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mlistView = (ListView) findViewById(R.id.dictionary_list_view);
        CatList1Acdapter mAdapter = new CatList1Acdapter(DictionaryActivity.this, countryNames, count, countryFlags);
        //LinearLayoutManager linearLayout = new LinearLayoutManager(DictionaryActivity.this);
       mlistView.setAdapter(mAdapter);

        intent = new Intent(DictionaryActivity.this, CatListActivity.class);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        intent.putExtra("contentId", "Long");
                        intent.putExtra("title","장모종");
                        break;

                    case 1:
                        intent.putExtra("contentId", "Middle");
                        intent.putExtra("title","중모종");
                        break;

                    case 2:
                        intent.putExtra("contentId", "Short");
                        intent.putExtra("title","단모종");
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
