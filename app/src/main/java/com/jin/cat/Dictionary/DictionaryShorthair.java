package com.jin.cat.Dictionary;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jin.cat.R;

public class DictionaryShorthair extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dictionary_shorthair);

        findViewById(R.id.short1).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.short1:
                startActivity(new Intent(this, Dialog.class));
                break;
        }
    }
}