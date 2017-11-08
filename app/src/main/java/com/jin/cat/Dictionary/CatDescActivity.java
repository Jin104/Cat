package com.jin.cat.Dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jin.cat.R;

public class CatDescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_desc);

        Intent intent = getIntent();
        String hair = intent.getExtras().getString("hair");
        String name = intent.getExtras().getString("name");

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }
}
