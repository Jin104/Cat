package com.jin.cat.Dictionary.Longhair;

import android.os.StrictMode;
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

import java.util.ArrayList;
import java.util.List;

public class LonghairActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Longhair> result;
    private LonghairAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longhair);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Cats").child("Long");

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.longhair_list_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayout = new GridLayoutManager(LonghairActivity.this, 2);
        recyclerView.setLayoutManager(gridLayout);


        adapter = new LonghairAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();

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
                result.add(dataSnapshot.getValue(Longhair.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Longhair longhair = dataSnapshot.getValue(Longhair.class);

                int index = getItemIndex(longhair);

                result.set(index, longhair);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Longhair longhair = dataSnapshot.getValue(Longhair.class);

                int index = getItemIndex(longhair);

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

    private int getItemIndex(Longhair longhair) {

        int index = -1;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).key.equals(longhair.key)) {
                index = i;
                break;
            }
        }
        return index;
    }

}