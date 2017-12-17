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

import com.jin.cat.Knowledge.Food.IntroSlider.WelcomeActivity;
import com.jin.cat.R;
import com.jin.cat.adapter.FoodAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FoodOneActivity extends AppCompatActivity {

    private String CLIENT_ID = "h0jOnpEEU05opv5JOxw9";//애플리케이션 클라이언트 아이디값";
    private String CLIENT_SECRET = "gq56_i7NEP";//애플리케이션 클라이언트 시크릿값";
    private ListView mListView;

    private List<String> contents;
    private List<String> images;

  //  private String key;
    private boolean isSearch = false;
    // ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_one);

        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PrefManager prefManager = new PrefManager(getApplicationContext());
//
//                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(FoodOneActivity.this, WelcomeActivity.class));
                finish();
            }
        });

        setTitle("건 식");

        contents = new ArrayList<String>();
        images = new ArrayList<String>();

        final CheckBox checkFirst = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkSecond = (CheckBox) findViewById(R.id.checkBox2);


            checkFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (checkFirst.isChecked()) {
                        isSearch = true;

                        new Thread() {
                            public void run() {
                                ShoppingApi("고양이 사료 오가닉");
                            }
                        }.start();
                    }
                    else {
                        contents.clear();
                        images.clear();
                    }

                    while(isSearch);

                    mListView = (ListView) findViewById(R.id.listView6);
                    mListView.setAdapter(new FoodAdapter(FoodOneActivity.this, contents, images));

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String name = contents.get(i);
                            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        });


//        checkSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (checkSecond.isChecked()) {
//                    isSearch = true;
//
//                    new Thread() {
//                        public void run() {
//                            ShoppingApi("고양이 사료 프리미엄");
//                        }
//                    }.start();
//                }
//                else {
//                    contents.clear();
//                    images.clear();
//                }
//
//                while(isSearch);
//
//                mListView = (ListView) findViewById(R.id.listView6);
//                mListView.setAdapter(new FoodAdapter(FoodOneActivity.this, contents, images));
//
//                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        String name = contents.get(i);
//                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        mListView = (ListView) findViewById(R.id.listView6);
        mListView.setAdapter(new FoodAdapter(FoodOneActivity.this, contents, images));
    }

    public void ShoppingApi(String key) {
        try {
            String text = URLEncoder.encode(key, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/shop.json?query="+ text+ "display=10" + "&start=1"; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query=" + text + "&start=1&target=shop&short=data"; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(br);

            int parserEvent = parser.getEventType();

            boolean isFirst = true;
            boolean isTitle = false;
            boolean isImage = false;

            String title = "";
            String image = "";

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("title")){
                            isTitle = true;
                        }
                        else if(parser.getName().equals("image")){ //title 만나면 내용을 받을수 있게 하자
                            isImage = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(isTitle){
                            title = parser.getText();
                            if(isFirst){
                                isFirst = false;
                            } else {
                                contents.add(title);
                            }

                            isTitle = false;
                        }
                        if(isImage){
                            image = parser.getText();
                            images.add(image);

                            isImage = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }

            isSearch = false;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
