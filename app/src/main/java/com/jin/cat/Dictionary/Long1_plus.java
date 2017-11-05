package com.jin.cat.Dictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-10-22.
 */

public class Long1_plus extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.long1_plus);

    }
}
