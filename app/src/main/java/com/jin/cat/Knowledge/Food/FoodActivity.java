package com.jin.cat.Knowledge.Food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.Knowledge.Food.IntroSlider.WelcomeActivity;
import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

public class FoodActivity extends AppCompatActivity {
    private boolean isFirstOne = true;
    private boolean isFirstTwo = true;

    private ListView mListView;

    private Intent welcomeOneIntent;
    private Intent oneIntent;

    private Intent welcomeTwoIntent;
    private Intent twoIntent;

    private String[] countryNames = {"건 식", "습 식"};
    private int[] countryFlags = {
            R.drawable.one,
            R.drawable.two,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        welcomeOneIntent = new Intent(FoodActivity.this, WelcomeActivity.class);
        oneIntent = new Intent(FoodActivity.this, FoodOneActivity.class);

        welcomeTwoIntent = new Intent(FoodActivity.this, WelcomeActivity.class);
        twoIntent = new Intent(FoodActivity.this, FoodOneActivity.class);

        setTitle("Food");

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter myAdapter = new LanguageAdapter(FoodActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    if (isFirstOne) {
                        startActivity(welcomeOneIntent);
                        isFirstOne = false;
                    } else {
                        startActivity(oneIntent);
                    }
                }
                else if (position == 1) {
                    if (isFirstTwo) {
                        startActivity(welcomeTwoIntent);
                        isFirstTwo = false;
                    } else {
                        startActivity(twoIntent);
                    }
                }
            }
        });
    }
}
