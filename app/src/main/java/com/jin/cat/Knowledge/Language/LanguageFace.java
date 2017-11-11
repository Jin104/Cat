package com.jin.cat.Knowledge.Language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.jin.cat.R;

public class LanguageFace extends AppCompatActivity {

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

        setTitle("얼굴언어");

        mGridView = (GridView)findViewById(R.id.gridview3);
        FaceAdapter myAdapter = new FaceAdapter(LanguageFace.this, countryNames, countryFlags);

        mGridView.setAdapter(myAdapter);
    }
}
