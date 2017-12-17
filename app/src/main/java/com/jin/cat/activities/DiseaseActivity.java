package com.jin.cat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private RecyclerView list;
    private RecyclerView.LayoutManager layoutManager;
    private List<ExpandableList> result = new ArrayList<>();
    private com.jin.cat.adapter.ExpandableListAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        setTitle("응급처치");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Knowledge").child("Health").child("병");

        list = (RecyclerView)findViewById(R.id.expandableGoods);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        adapter = new com.jin.cat.adapter.ExpandableListAdapter(result);
        list.setAdapter(adapter);
        updateList();
    }

    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(ExpandableList.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ExpandableList item = dataSnapshot.getValue(ExpandableList.class);

                int index = getItemIndex(item);

                result.set(index, item);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ExpandableList item = dataSnapshot.getValue(ExpandableList.class);

                int index = getItemIndex(item);

                result.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(ExpandableList item) {

        int index = -1;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getKey().equals(item.getKey())) {
                index = i;
                break;
            }
        }
        return index;
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

    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
        }
        return super.onContextItemSelected(item);
    }
}
