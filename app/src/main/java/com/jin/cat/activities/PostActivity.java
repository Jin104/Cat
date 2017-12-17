package com.jin.cat.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.adapter.ViewPagerAdapter;
import com.jin.cat.models.Cat;
import com.jin.cat.models.Post;
import com.jin.cat.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PostActivity extends AppCompatActivity {

    private String postID;
    private String postTYPE;
    private TextView mComment;
    private ImageButton mDeleteBtn;

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        postID = intent.getStringExtra("postID");
        postTYPE = intent.getStringExtra("postType");
        mComment = (TextView)findViewById(R.id.post_go_comment);

        final ImageView postImage = (ImageView)findViewById(R.id.post_img);
        final ImageView userImage = (ImageView)findViewById(R.id.post_user_img);
        final TextView userName = (TextView) findViewById(R.id.post_user_name);
        final TextView userEmail = (TextView) findViewById(R.id.post_user_email);
        final TextView postTitle = (TextView)findViewById(R.id.post_title);
        final TextView postDesc = (TextView) findViewById(R.id.post_desc);
        final TextView postTime = (TextView) findViewById(R.id.post_time);
        final TextView postType = (TextView) findViewById(R.id.post_type);

        mDeleteBtn = (ImageButton)findViewById(R.id.post_delete);

        TextView comment = (TextView) findViewById(R.id.post_go_comment);

        SpannableStringBuilder builder = new SpannableStringBuilder(">");
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        comment.append(builder);


        FirebaseUtils.getPostRef().child(postTYPE).child(postID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Context postImageContext = postImage.getContext();
                Context userImageContext = userImage.getContext();

                post = dataSnapshot.getValue(Post.class);

                if(post.getPostImageUrl()!=null)
                {
                    Picasso.with(postImageContext).load(post.getPostImageUrl()).into(postImage);
                }

                Picasso.with(userImageContext).load(post.getUser().getImageUrl()).into(userImage);
                userName.setText(post.getUser().getUser());
                userEmail.setText(post.getUser().getEmail());
                postTitle.setText(post.getPostTitle());
                postDesc.setText(post.getPostDesc());
                postTime.setText(DateUtils.getRelativeTimeSpanString(post.getTimeCreated()));
                postType.setText("["+post.getPostType()+"]");

                if(FirebaseUtils.getCurrentUser().getUid().equals(post.getUser().getUid())){
                    mDeleteBtn.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, PostCommentActivity.class);
                intent.putExtra("postID", postID);
                intent.putExtra("postTYPE", postTYPE);
                startActivity(intent);
            }
        });

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(PostActivity.this);
                ab.setTitle("주의");
                ab.setMessage("삭제하시겠습니까?");

                ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //mDatabaseReference.child(inoculation.getUid()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("My_Posts").child(FirebaseUtils.getCurrentUser().getUid()).child(post.getPostId()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Posts").child("All").child(post.getPostId()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Posts").child(post.getPostType()).child(post.getPostId()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("Post_Comments").child(post.getPostId()).removeValue();

                        finish();

                    }
                });

                ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
