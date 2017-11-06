package com.jin.cat.Knowledge.Health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
//import android.widget.ListView;

import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-10-28.
 */

public class HealthActivity extends AppCompatActivity {

    Toolbar mToolbar;
    GridView mGridView;

    Intent mfrist;
    Intent msecond;
    Intent mthird;
    Intent mfourth;
    Intent mfifth;
    Intent msixth;

    String[] countryNames = {"토", "응가", "질병", "중성화", "외상", "기타"};
    int[] countryFlags = {R.drawable.cat_one,
            R.drawable.cat_two,
            R.drawable.cat_three,
            R.drawable.cat_one,
            R.drawable.cat_two,
            R.drawable.cat_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health);

        mfrist = new Intent(HealthActivity.this, HealthOne.class);
        msecond = new Intent(HealthActivity.this, HealthTwo.class);
        mthird = new Intent(HealthActivity.this, HealthThree.class);
        mfourth = new Intent(HealthActivity.this, HealthFour.class);
        mfifth = new Intent(HealthActivity.this, HealthFive.class);
        msixth = new Intent(HealthActivity.this, HealthSix.class);


        mToolbar = (Toolbar) findViewById(R.id.toolbar3);
        mToolbar.setTitle(getResources().getString(R.string.app_name_health));

//        mListView = (ListView) findViewById(R.id.listView3);
        mGridView = (GridView)findViewById(R.id.gridview3);
        HealthAdapter myAdapter = new HealthAdapter(HealthActivity.this, countryNames, countryFlags);

        mGridView.setAdapter(myAdapter);
//        mListView.setAdapter(myAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    startActivity(mfrist);
                else if (position == 1)
                    startActivity(msecond);
                else if (position == 2)
                    startActivity(mthird);
                else if (position == 3)
                    startActivity(mfourth);
                else if (position == 4)
                    startActivity(mfifth);
                else if (position == 5)
                    startActivity(msixth);
            }
        });
    }
}
