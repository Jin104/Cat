package com.jin.cat.Knowledge.DoNotEat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-11-17.
 */

public class DoNotEatActivity extends AppCompatActivity {

    private GridView mGridView;
    private Intent mFirst;

    private String[] countryNames = {"육류", "채소", "과일", "해산물", "유제품", "기타"};
    private int[] countryFlags = {R.drawable.grape, R.drawable.grape,
            R.drawable.grape, R.drawable.grape, R.drawable.grape, R.drawable.grape};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_do_not_eat);

         mFirst = new Intent(DoNotEatActivity.this, DoNotEatMeat.class);

        setTitle("DoNotEat");
        mGridView = (GridView)findViewById(R.id.donotEat_gridview);
        DoNotEatAdapter myAdapter = new DoNotEatAdapter(DoNotEatActivity.this, countryNames, countryFlags);
        mGridView.setAdapter(myAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) startActivity(mFirst);
            }
        });
    }
}
