package com.jin.cat.Knowledge.Food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.jin.cat.Knowledge.Language.LanguageAdapter;
import com.jin.cat.R;

public class FoodActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;

    Intent mfrist;
    Intent msecond;

    String[] countryNames = {"건 식", "습 식"};
    int[] countryFlags = {
            R.drawable.one,
            R.drawable.two,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

//        mfrist = new Intent(FoodActivity.this, .class);
//        msecond = new Intent(FoodActivity.this, .class);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setTitle("Food");

        mListView = (ListView) findViewById(R.id.listView2);
        LanguageAdapter myAdapter = new LanguageAdapter(FoodActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0)
//                    startActivity(mfrist);
//                else if (position == 1)
//                    startActivity(msecond);
//            }
//        });
    }
}
