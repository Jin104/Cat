package com.jin.cat.Knowledge.Food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.Knowledge.Food.IntroSlider.PrefManager;
import com.jin.cat.Knowledge.Food.IntroSlider.WelcomeActivity_Two;
import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

public class FoodTwoActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"먹이1", "먹이2", "먹이3"};
    private int[] countryFlags = {
            R.drawable.food_two,
            R.drawable.food_two,
            R.drawable.food_two};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_two);

        findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PrefManager prefManager = new PrefManager(getApplicationContext());

                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(FoodTwoActivity.this, WelcomeActivity_Two.class));
                finish();
            }
        });


        setTitle("습식");

        mListView = (ListView) findViewById(R.id.listView7);
        LanguageAdapter myAdapter = new LanguageAdapter(FoodTwoActivity.this, countryNames, countryFlags);

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
