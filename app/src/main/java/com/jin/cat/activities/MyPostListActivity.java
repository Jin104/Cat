package com.jin.cat.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jin.cat.R;
import com.jin.cat.adapter.CatListAdapter;
import com.jin.cat.adapter.PostListAdapter;
import com.jin.cat.models.Cat;
import com.jin.cat.models.Post;
import com.jin.cat.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by rakha on 2017-12-17.
 */

public class MyPostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> result;
    private PostListAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_list);         //post_list_view

        setTitle("내가 작성한 글");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("My_Posts").child(FirebaseUtils.getCurrentUser().getUid());

        result = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.post_list_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyPostListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PostListAdapter(result);
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
                result.add(dataSnapshot.getValue(Post.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Post post = dataSnapshot.getValue(Post.class);

                int index = getItemIndex(post);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Post post = dataSnapshot.getValue(Post.class);
//
//                int index = getItemIndex(post);
//
//                result.remove(index);
//                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(Post post) {

        int index = -1;

        for (int i = 0; i < result.size(); i++) {
//            if (result.get(i).getKey().equals(post.getKey())) {
//                index = i;
//                break;
//            }
            index = i;
        }
        return index;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
