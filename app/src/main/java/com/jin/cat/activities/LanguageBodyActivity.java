package com.jin.cat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.jin.cat.Knowledge.Language.FaceAdapter;
import com.jin.cat.R;

/**
 * Created by inhye on 2017-11-14.
 */

public class LanguageBodyActivity extends AppCompatActivity {

    private GridView mGridView;

    private String[] countryNames = {"첫번째", "두번째", "세번째", "네번째", "다섯번째", "여섯번째", "일곱번째"};
    private int[] countryFlags = {R.drawable.one,
            R.drawable.nine,
            R.drawable.three,
            R.drawable.cat4,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_gridview);

        setTitle("행동언어");

        mGridView = (GridView) findViewById(R.id.row_gridview);
        FaceAdapter myAdapter = new FaceAdapter(LanguageBodyActivity.this, countryNames, countryFlags);

        mGridView.setAdapter(myAdapter);

    }
}

