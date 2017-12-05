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
import com.jin.cat.Knowledge.Food.IntroSlider.WelcomeActivity;
import com.jin.cat.R;
import com.jin.cat.adapter.LanguageAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FoodOneActivity extends AppCompatActivity {

    private String CLIENT_ID="h0jOnpEEU05opv5JOxw9";//애플리케이션 클라이언트 아이디값";
    private String CLIENT_SECRET = "gq56_i7NEP";//애플리케이션 클라이언트 시크릿값";
    private ListView mListView;

    private String[] countryNames = {"먹이1", "먹이2", "먹이3"};
    private int[] countryFlags = {
            R.drawable.food_one,
            R.drawable.food_two,
            R.drawable.cat4};

    private String[] countryNames2 = {"안녕", "응 안녕", "어 안녕"};
    private int[] countryFlags2 = {
            R.drawable.cat,
            R.drawable.cat1,
            R.drawable.cat2};

    private List<String> names;
    private List<Integer> flags;

    // ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_one);

        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrefManager prefManager = new PrefManager(getApplicationContext());

                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(FoodOneActivity.this, WelcomeActivity.class));
                finish();
            }
        });

        setTitle("건식");

        names = new ArrayList<String>();
        flags = new ArrayList<Integer>();

        final CheckBox checkFirst = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkSecond = (CheckBox) findViewById(R.id.checkBox2);

        checkFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkFirst.isChecked() && !(checkSecond.isChecked())) {
//                    for (String name : countryNames) {
//                        names.add(name);
//                    }
//                    ShoppingApi();
//                    for (int flag : countryFlags) {
//                        flags.add(flag);
//                    }

                    ShoppingApi();
                }

                else if (checkFirst.isChecked() && (checkSecond.isChecked())) {
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
                    for (String removeName : removeNames) {
                        names.remove(removeName);
                    }
                    for (Integer removeFlag : removeFlags) {
                        flags.remove(removeFlag);
                    }
                    for (String name : countryNames) {
                        names.add(name);
                    }

                    for (int flag : countryFlags) {
                        flags.add(flag);
                    }
                    for (String name : countryNames2) {
                        names.add(name);
                    }

                    for (int flag : countryFlags2) {
                        flags.add(flag);
                    }
                }
                else {
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

                for (int val : flags)
                {
                    flagList[index++] = val;
                }
                //리스트 정렬 (names 랑 flags 동시에 )

                mListView = (ListView) findViewById(R.id.listView6);
                mListView.setAdapter(new LanguageAdapter(FoodOneActivity.this, nameList, flagList));

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
                } else if (checkFirst.isChecked() && (checkSecond.isChecked())) {
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
                    for (String removeName : removeNames) {
                        names.remove(removeName);
                    }
                    for (Integer removeFlag : removeFlags) {
                        flags.remove(removeFlag);
                    }
                    for (String name : countryNames) {
                        names.add(name);
                    }

                    for (int flag : countryFlags) {
                        flags.add(flag);
                    }
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
                mListView = (ListView) findViewById(R.id.listView6);
                mListView.setAdapter(new LanguageAdapter(FoodOneActivity.this, nameList, flagList));

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String name = names.get(i);
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mListView = (ListView) findViewById(R.id.listView6);

        String[] nameList = new String[names.size()];
        System.arraycopy(names.toArray(), 0, nameList, 0, nameList.length);

        int[] flagList = new int[names.size()];
        int index = 0;
        for (int val : flags) {
            flagList[index++] = val;
        }
        mListView.setAdapter(new LanguageAdapter(FoodOneActivity.this, nameList, flagList));
    }

    public void ShoppingApi()
    {
            try {
            String text = URLEncoder.encode("고양이사료", "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/shop.json?query="+ text+ "display=10" + "&start=1"; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query="+ text + "display=10" + "&start=1"; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
//            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
//                response.append(inputLine);
                names.add(inputLine);
            }
            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
