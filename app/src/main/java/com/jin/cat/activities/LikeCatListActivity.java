package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jin.cat.R;
import com.jin.cat.adapter.CatListAdapter;
import com.jin.cat.models.Cat;
import com.jin.cat.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LikeCatListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cat> result;
    private CatListAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);

        setTitle("좋아요 누른 고양이들");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User_Liked_Cat").child(FirebaseUtils.getCurrentUser().getUid());

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.cat_list_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayout = new GridLayoutManager(LikeCatListActivity.this, 2);
        recyclerView.setLayoutManager(gridLayout);


        adapter = new CatListAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();

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

    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(Cat.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Cat cat = dataSnapshot.getValue(Cat.class);

                int index = getItemIndex(cat);

                result.set(index, cat);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Cat cat = dataSnapshot.getValue(Cat.class);

                int index = getItemIndex(cat);

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

    private int getItemIndex(Cat cat) {

        int index = -1;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getKey().equals(cat.getKey())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
