package com.jin.cat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.jin.cat.R;
import com.jin.cat.adapter.GoodsAdapter;
import com.jin.cat.models.ExpandableList;
import com.jin.cat.models.Goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-12-06.
 */

public class GoodsActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;

    private List<Goods> goodsItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        goodsItem = new ArrayList<>();

        /*for(int i=0; i<=10; i++) {
            Goods goods = new Goods("head" , "desc");
            goodsItem.add(goods);
        }*/
        Goods goods = new Goods("사료와 물" , "적정량을 급여해야 한다.");
        goodsItem.add(goods);

        adapter = new GoodsAdapter(goodsItem, this);

        recycler.setAdapter(adapter);
    }

}
