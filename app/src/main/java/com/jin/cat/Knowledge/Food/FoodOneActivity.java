package com.jin.cat.Knowledge.Food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

public class FoodOneActivity extends AppCompatActivity {

    ListView mListView;

    String[] countryNames = {"먹이1", "먹이2", "먹이3"};
    int[] countryFlags = {
            R.drawable.food_one,
            R.drawable.food_one,
            R.drawable.food_one};

   // ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_one);

        setTitle("행동언어");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listView5);
        LanguageAdapter myAdapter = new LanguageAdapter(FoodOneActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0)
//                    startActivity(new Intent(FoodOneActivity.this, .class));
//                else if (position == 1)
//                    startActivity(new Intent(FoodOneActivity.this, .class));
//                else if (position == 2)
//                    startActivity(new Intent(FoodOneActivity.this, .class));
            }
        });
    }
}
