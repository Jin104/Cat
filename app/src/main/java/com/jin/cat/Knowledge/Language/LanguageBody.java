package com.jin.cat.Knowledge.Language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.jin.cat.R;

public class LanguageBody extends AppCompatActivity {

    Toolbar mToolbar;
    GridView mGridView;

    String[] countryNames = {"첫번째", "두번째", "세번째", "네번째", "다섯번째", "여섯번째","일곱번째"};
    int[] countryFlags = {R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health);

        mToolbar = (Toolbar) findViewById(R.id.toolbar3);
        mToolbar.setTitle("행동언어");

        mGridView = (GridView)findViewById(R.id.gridview3);
        FaceAdapter myAdapter = new FaceAdapter(LanguageBody.this, countryNames, countryFlags);

        mGridView.setAdapter(myAdapter);
    }
}
