package com.jin.cat.Knowledge.DoNotEat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jin.cat.Knowledge.DoNotEat.Adapter.MyAdapter;
import com.jin.cat.Knowledge.DoNotEat.Model.Item;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmoon on 2017-11-17.
 */

public class DoNotEatMeat extends AppCompatActivity {

    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;
    List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_not_eat);
        setTitle("육류");

        list = (RecyclerView)findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        setData();
    }

    private void setData() {
        for(int i=0; i<20; i++) {
            /*if(i%2==0) {
                Item item = new Item("This is item "+(i+1), "This is child item "+(i+1),true);
                items.add(item);
            }
            else {
                Item item = new Item("This is item "+(i+1), "",false);
                items.add(item);
            }*/
            Item item = new Item("This is item "+(i+1), "This is child item"+(i+1),true);
            items.add(item);
        }
        MyAdapter adapter = new MyAdapter(items);
        list.setAdapter(adapter);
    }
}
