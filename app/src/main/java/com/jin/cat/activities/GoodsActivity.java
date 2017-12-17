package com.jin.cat.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jin.cat.R;
import com.jin.cat.adapter.CustomExpandableListAdapter;
import com.jin.cat.models.ExpandableList;
import com.jin.cat.models.Goods;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-12-06.
 */

public class GoodsActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private ExpandableListAdapter goodsAdapter;
    private List<String> goodsTitle;
    private HashMap<String, List<String> > goodsDetail;

    private List<Goods> result = new ArrayList<>();

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);


        setTitle("용품");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Knowledge").child("Goods");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableGoods);
        goodsDetail = new HashMap<String, List<String>>();
        goodsTitle = new ArrayList<String>();

        updateList();

        goodsAdapter = new CustomExpandableListAdapter(this, goodsTitle, goodsDetail);
        expandableListView.setAdapter(goodsAdapter);
    }

    private void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Goods item = dataSnapshot.getValue(Goods.class);
                goodsTitle.add(item.getTitle());

                List<String> list = new ArrayList<String>();
                list.add(item.getDesc());
                goodsDetail.put(item.getTitle(), list);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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


/*
public class GoodsActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;

    private List<Goods> goodsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        setTitle("용품");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        goodsItem = new ArrayList<>();

        */
/*for(int i=0; i<=10; i++) {
            Goods goods = new Goods("head" , "desc");
            goodsItem.add(goods);
        }*//*

        Goods goods = new Goods("사료와 물" , "적정량을 급여해야 한다.");
        goodsItem.add(goods);

        adapter = new GoodsAdapter(goodsItem, this);

        recycler.setAdapter(adapter);
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
*/
