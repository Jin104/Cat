package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jin.cat.adapter.SexAdapter;
import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-11-17.
 */

public class SexActivity extends AppCompatActivity {

    private GridView mGridView;
    private Intent mFirst;

    private String[] countryNames = {"발정", "발정 과정", "교배 적기", "중성화 수술", "임신", "분만"};
    private int[] countryFlags = {R.drawable.grape, R.drawable.grape,
            R.drawable.grape, R.drawable.grape, R.drawable.grape, R.drawable.grape};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);


        setTitle("성");
        mGridView = (GridView)findViewById(R.id.donotEat_gridview);
        SexAdapter myAdapter = new SexAdapter(SexActivity.this, countryNames, countryFlags);
        mGridView.setAdapter(myAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
