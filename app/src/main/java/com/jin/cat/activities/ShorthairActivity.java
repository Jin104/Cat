package com.jin.cat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jin.cat.models.Cat;
import com.jin.cat.adapter.CatListAdapter;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

public class ShorthairActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Cat> result;
    private CatListAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);

        setTitle("단모종");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Cats").child("Short");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.cat_list_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayout = new GridLayoutManager(ShorthairActivity.this, 2);
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

        Toast.makeText(ShorthairActivity.this, "0",Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(ShorthairActivity.this, "0",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(ShorthairActivity.this, "1",Toast.LENGTH_SHORT).show();
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
}
