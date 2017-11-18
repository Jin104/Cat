package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.models.Comment;
import com.jin.cat.models.User;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

public class CommentActivity extends AppCompatActivity {

    private Comment mComment;

    private static String hairId;
    private static String catId;

    private EditText editComment;
    private ImageButton commentSend;

    private RecyclerView mCommentList;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUid;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setTitle("댓글");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        hairId = intent.getExtras().getString("hairId");
        catId = intent.getExtras().getString("catId");

        editComment = (EditText)findViewById(R.id.edit_comment);
        commentSend = (ImageButton)findViewById(R.id.comment_send);

        databaseReference = database.getInstance().getReference().child("Cat_Comments").child(hairId).child(catId);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();


        mCommentList = (RecyclerView) findViewById(R.id.comment_list);
        mCommentList.setHasFixedSize(true);
        mCommentList.setLayoutManager(new LinearLayoutManager(CommentActivity.this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Comment, CommentViewHolder> FBRA = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(

                Comment.class,
                R.layout.row_comment,
                CommentViewHolder.class,
                databaseReference
        ) {

            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {

                Glide.with(CommentActivity.this)
                        .load(model.getUser().getImageUrl())
                        .into(viewHolder.commentImage);

                viewHolder.setUsername(model.getUser().getUser());
                viewHolder.setTime(DateUtils.getRelativeTimeSpanString(model.getTimeCreated()));
                viewHolder.setDesc(model.getComment());

            }
        };
        mCommentList.setAdapter(FBRA);
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView commentImage;
        TextView commentUsername;
        TextView commentTime;
        TextView commentDesc;

        public CommentViewHolder(View itemView) {
            super(itemView);

            commentImage = (ImageView) itemView.findViewById(R.id.comment_image);
            commentUsername = (TextView) itemView.findViewById(R.id.comment_username);
            commentTime = (TextView) itemView.findViewById(R.id.comment_time);
            commentDesc = (TextView) itemView.findViewById(R.id.comment_desc);
        }

        public void setUsername(String name){
            commentUsername.setText(name);
        }

        public void setTime(CharSequence time){
            commentTime.setText(time);
        }

        public void setDesc(String desc){
            commentDesc.setText(desc);
        }

    }

    public void commentSendButtonClicked(View view){

        final String commentValue = editComment.getText().toString().trim();

        if(FirebaseUtils.getCurrentUser()!=null) {
            if (!TextUtils.isEmpty(commentValue)) {

                final String commentId = FirebaseUtils.getUid();

                mComment = new Comment();
                mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
                mDatabaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                User user = dataSnapshot.getValue(User.class);

                                mComment.setUser(user);
                                mComment.setComment(commentValue);
                                mComment.setCommentId(commentId);
                                mComment.setTimeCreated(System.currentTimeMillis());

                                FirebaseUtils.getCatCommentRef(hairId,catId).child(commentId).setValue(mComment);

                                editComment.setText("");
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

            }
        }else{

            Snackbar.make(view,  Html.fromHtml("<font color=\"#ffffff\">로그인 하시겠습니까?</font>"), 2000).setActionTextColor(Color.parseColor("#FF0000")).setAction("YES", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CommentActivity.this, LoginActivity.class));
                }
            }).show();
        }

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
