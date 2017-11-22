package com.jin.cat.Knowledge.Food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.jin.cat.Knowledge.Food.IntroSlider.PrefManager;
import com.jin.cat.Knowledge.Food.IntroSlider.WelcomeActivity_Two;
import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodTwoActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] countryNames = {"만나서", "먹이2", "먹이3"};
    private int[] countryFlags = {
            R.drawable.food_one,
            R.drawable.food_two,
            R.drawable.cat4};

    private String[] countryNames2 = {"안녕", "나는", "인헤야"};
    private int[] countryFlags2 = {
            R.drawable.cat,
            R.drawable.cat1,
            R.drawable.cat2};

    private List<String> names;
    private List<Integer> flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_two);

        findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrefManager prefManager = new PrefManager(getApplicationContext());

                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(FoodTwoActivity.this, WelcomeActivity_Two.class));
                finish();
            }
        });


        setTitle("습식");

        names = new ArrayList<String>();
        flags = new ArrayList<Integer>();

        final CheckBox checkFirst = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkSecond = (CheckBox) findViewById(R.id.checkBox2);

        checkFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkFirst.isChecked()) {
                    for (String name : countryNames) {
                        names.add(name);
                    }

                    for (int flag : countryFlags) {
                        flags.add(flag);
                    }
                } else {
                    List<String> removeNames = new ArrayList<String>();
                    List<Integer> removeFlags = new ArrayList<Integer>();

                    for (String name : names) {
                        for (String firstName : countryNames) {
                            if (name.equals(firstName)) {
                                int index = names.indexOf(name);
                                int flag = flags.get(index);

                                removeNames.add(name);
                                removeFlags.add(flag);
                            }
                        }
                    }

                    for (String removeName : removeNames) {
                        names.remove(removeName);
                    }
                    for (Integer removeFlag : removeFlags) {
                        flags.remove(removeFlag);
                    }
                }

                String[] nameList = new String[names.size()];
                System.arraycopy(names.toArray(), 0, nameList, 0, nameList.length);

                int[] flagList = new int[names.size()];
                int index = 0;
                for (int val : flags) {
                    flagList[index++] = val;
                }
                mListView = (ListView) findViewById(R.id.listView7);
                mListView.setAdapter(new LanguageAdapter(FoodTwoActivity.this, nameList, flagList));

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String name = names.get(i);
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        checkSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkSecond.isChecked()) {
                    for (String name : countryNames2) {
                        names.add(name);
                    }

                    for (int flag : countryFlags2) {
                        flags.add(flag);
                    }
                } else {
                    List<String> removeNames = new ArrayList<String>();
                    List<Integer> removeFlags = new ArrayList<Integer>();

                    for (String name : names) {
                        for (String firstName : countryNames2) {
                            if (name.equals(firstName)) {
                                int index = names.indexOf(name);
                                int flag = flags.get(index);

                                removeNames.add(name);
                                removeFlags.add(flag);
                            }
                        }
                    }

                    for (String removeName : removeNames) {
                        names.remove(removeName);
                    }
                    for (Integer removeFlag : removeFlags) {
                        flags.remove(removeFlag);
                    }
                }

                String[] nameList = new String[names.size()];
                System.arraycopy(names.toArray(), 0, nameList, 0, nameList.length);

                int[] flagList = new int[names.size()];
                int index = 0;
                for (int val : flags) {
                    flagList[index++] = val;
                }
                mListView = (ListView) findViewById(R.id.listView7);
                mListView.setAdapter(new LanguageAdapter(FoodTwoActivity.this, nameList, flagList));

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String name = names.get(i);
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mListView = (ListView) findViewById(R.id.listView7);

        String[] nameList = new String[names.size()];
        System.arraycopy(names.toArray(), 0, nameList, 0, nameList.length);

        int[] flagList = new int[names.size()];
        int index = 0;
        for (int val : flags) {
            flagList[index++] = val;
        }
        mListView.setAdapter(new LanguageAdapter(FoodTwoActivity.this, nameList, flagList));
    }
}
