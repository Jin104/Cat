package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jin.cat.R;
import com.jin.cat.models.Post;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by rakha on 2017-12-17.
 */

public class MyPostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private MyPostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_list);         //post_list_view

        setTitle("내가 작성한 글");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("My_Posts").child(FirebaseUtils.getCurrentUser().getUid());

        recyclerView = (RecyclerView) findViewById(R.id.post_list_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyPostListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new MyPostListActivity.MyPostListAdapter(MyPostListActivity.this, reference);
        recyclerView.setAdapter(adapter);

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

    private static class MyPostListViewHolder extends RecyclerView.ViewHolder{

        public TextView postTitle;
        public ImageView postImage;
        public TextView postUser;
        public TextView postTime;
        public TextView postNumComments;
        public LinearLayout linearLayout;


        public MyPostListViewHolder(View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.board_title);
            postImage = (ImageView) itemView.findViewById(R.id.board_img);
            postUser = (TextView) itemView.findViewById(R.id.board_name);
            postTime = (TextView) itemView.findViewById(R.id.board_time);
            postNumComments = (TextView) itemView.findViewById(R.id.board_comment);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.board_layout);
        }

        public void setImage(Context context, String image){
            Picasso.with(context).load(image).into(postImage);
        }
    }

    private class MyPostListAdapter extends RecyclerView.Adapter<MyPostListViewHolder>{

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mPostId = new ArrayList<>();
        private List<Post> mPost = new ArrayList<>();

        public MyPostListAdapter(final Context context, DatabaseReference ref){

            mContext = context;
            mDatabaseReference = ref;
            Query query = mDatabaseReference.orderByChild("timeCreated");

            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Post post = dataSnapshot.getValue(Post.class);

                    mPostId.add(dataSnapshot.getKey());
                    mPost.add(post);
                    notifyItemInserted(mPost.size()-1);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Post newPost = dataSnapshot.getValue(Post.class);
                    String postKey = dataSnapshot.getKey();

                    int postIndex = mPostId.indexOf(postKey);
                    if(postIndex>-1){
                        mPost.set(postIndex, newPost);
                        notifyItemChanged(postIndex);
                    }else{

                    }
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
            };
            query.addChildEventListener(childEventListener);

            mChildEventListener = childEventListener;

        }



        @Override
        public MyPostListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.row_board, parent, false);
            return new MyPostListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyPostListViewHolder holder, int position) {
            final Post post = mPost.get(position);
            final Context context = holder.postImage.getContext();
            if(post.getPostImageUrl()!=null){
                holder.setImage(context, post.getPostImageUrl());
            }else{
                holder.postImage.setVisibility(View.GONE);
            }
            holder.postTitle.setText(post.getPostTitle());
            holder.postUser.setText(post.getUser().getUser());
            holder.postTime.setText(DateUtils.getRelativeTimeSpanString(post.getTimeCreated()));
            holder.postNumComments.setText(String.valueOf(post.getNumComments()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra("postID", post.getPostId());
                    intent.putExtra("postType", post.getPostType());

                    context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mPost.size();
        }
    }




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
