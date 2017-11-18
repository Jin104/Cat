package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jin.cat.adapter.HealthAdapter;
import com.jin.cat.Knowledge.Health.FirstAidActivity;
import com.jin.cat.R;


/**
 * Created by sunmoon on 2017-10-28.
 */

public class HealthActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"응급처치","예방접종","병"};
    private int[] countryFlags = {R.drawable.cat_one,
            R.drawable.cat_two,
            R.drawable.cat_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        setTitle("건강");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.health_listview);
        HealthAdapter myAdapter = new HealthAdapter(HealthActivity.this, countryNames, countryFlags);

        mListView.setAdapter(myAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:
                        startActivity(new Intent(HealthActivity.this, FirstAidActivity.class));
                        break;
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


//package com.jin.cat.Knowledge.Health;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
//        import android.view.View;
//        import android.widget.AdapterView;
//        import android.widget.GridView;
//
//        import com.jin.cat.R;
//
//
///**
// * Created by sunmoon on 2017-10-28.
// */
//
//public class HealthActivity extends AppCompatActivity {
//
//    private GridView mGridView;
//
//    private Intent mfrist;
////    Intent msecond;
////    Intent mThird;
//
//    private String[] countryNames = {"토", "응가", "질병", "중성화", "외상", "기타"};
//    private int[] countryFlags = {R.drawable.cat_one,
//            R.drawable.cat_two,
//            R.drawable.cat_three,
//            R.drawable.cat_one,
//            R.drawable.cat_two,
//            R.drawable.cat_three};
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.row_girdview);
//
//        mfrist = new Intent(HealthActivity.this, FirstAidActivity.class);
//        //      msecond = new Intent(HealthActivity.this, LanguageBodyActivity.class);
//        //      mThird = new Intent(HealthActivity.this, LanguageTailActivity.class);
//
//        setTitle("Health");
//
//        mGridView = (GridView) findViewById(R.id.row_grid_view);
//        HealthAdapter myAdapter = new HealthAdapter(HealthActivity.this, countryNames, countryFlags);
//
//        mGridView.setAdapter(myAdapter);
////        mListView.setAdapter(myAdapter);
//
//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0)
//                    startActivity(mfrist);
////                else if (position == 1)
////                    startActivity(msecond);
////                else if (position == 2)
////                    startActivity(mThird);
//            }
//        });
//    }
//}
