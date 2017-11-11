package com.jin.cat.Knowledge.Massage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmoon on 2017-10-30.
 */

public class MassageBasic extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.massage2);

        setTitle("기본");

        ImageButton btn = (ImageButton) findViewById(R.id.scroll_to_top_btn);

 //       ListView listView;
        MassageAdapter2 adapter;

        adapter = new MassageAdapter2();

        final  ListView listView = (ListView)findViewById(R.id.listView5);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cat_one), "음양론");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cat_two),"오행설");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cat_one),"경락과 경혈");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cat_two),"기본 테크닉");

        //ListView에 클릭 이벤트 핸들러 정의
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                MassageItem2 item = (MassageItem2) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                Drawable iconDrawable = item.getIcon();
            }
        });

        //ListView 상단으로 이동
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.smoothScrollToPosition(0);
            }
        });
    }
}
