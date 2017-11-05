package com.jin.cat.Knowledge.Massage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-11-06.
 */

public class MassageActivity extends AppCompatActivity implements View.OnClickListener {


    static final int[] tv = {
            R.id.textBasic,
            R.id.textLymph,
            R.id.textRelax,
            R.id.textTrouble,
            R.id.textEtc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.massage);

        Toolbar  mToolbar = (Toolbar) findViewById(R.id.toolbar4);
        mToolbar.setTitle(getResources().getString(R.string.app_name_massage));

        for(int tvId: tv) {
            TextView textView = (TextView)findViewById(tvId);
            textView.setOnClickListener(this);
        }
    }
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.textBasic:
                Intent intent1 = new Intent(this, MassageBasic.class);
                startActivity(intent1);
                break;
            case R.id.textLymph:
                Intent intent2 = new Intent(this, MassageLymph.class);
                startActivity(intent2);
                break;
            case R.id.textRelax:
                Intent intent3 = new Intent(this, MassageRelax.class);
                startActivity(intent3);
                break;
            case R.id.textTrouble:
                Intent intent4 = new Intent(this, MassageTrouble.class);
                startActivity(intent4);
                break;
            case R.id.textEtc:
                Intent intent5 = new Intent(this, MassageEtc.class);
                startActivity(intent5);
                break;
        }
    }
}

