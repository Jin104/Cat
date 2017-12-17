package com.jin.cat.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jin.cat.R;
import com.jin.cat.activities.CommentActivity;
import com.jin.cat.activities.LoginActivity;
import com.jin.cat.activities.PostActivity;
import com.jin.cat.activities.PostCreateActivity;
import com.jin.cat.dialogs.PostSearchDialog;
import com.jin.cat.models.Post;
import com.jin.cat.utils.FirebaseUtils;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    private View mView;
    private RecyclerView mPostRecyclerView;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mPostAdapter;

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private ImageButton mSearchBtn;

    private String postType;

    public BoardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_board, container, false);
        mButton1 = (Button)mView.findViewById(R.id.board_all);
        mButton2 = (Button)mView.findViewById(R.id.board_qna);
        mButton3 = (Button)mView.findViewById(R.id.board_etc);
        mSearchBtn = (ImageButton)mView.findViewById(R.id.board_search);

        mPostRecyclerView = (RecyclerView) mView.findViewById(R.id.board_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mPostRecyclerView.setLayoutManager(layoutManager);

        postType="All";
        setupAdapter("All");
        mPostRecyclerView.setAdapter(mPostAdapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton)mView.findViewById(R.id.board_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FirebaseUtils.getCurrentUser()!=null){
                    startActivity(new Intent(getActivity(), PostCreateActivity.class));
                }
                else{
                    Snackbar.make(mView,  Html.fromHtml("<font color=\"#ffffff\">로그인 하시겠습니까?</font>"), 2000).setActionTextColor(Color.parseColor("#FF0000")).setAction("YES", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    }).show();
                }
            }
        });



        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postType="All";
                setupAdapter("All");
                mPostRecyclerView.setAdapter(mPostAdapter);
                mButton1.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
                mButton2.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
                mButton3.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postType="질문";
                setupAdapter("질문");
                mPostRecyclerView.setAdapter(mPostAdapter);
                mButton1.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
                mButton2.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
                mButton3.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postType="기타";
                setupAdapter("기타");
                mPostRecyclerView.setAdapter(mPostAdapter);
                mButton1.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
                mButton2.setBackgroundColor(getContext().getResources().getColor(R.color.colorGray4));
                mButton3.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("postType", postType);
                PostSearchDialog dialog = new PostSearchDialog();
                dialog.setArguments(bundle);
                dialog.setTargetFragment(BoardFragment.this, 0);
                dialog.show(getFragmentManager(), null);

                //dialog.show(getFragmentManager(), null);

            }
        });
        return mView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String search = data.getExtras().getString("search");
        String type = data.getExtras().getString("searchType");


        //Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        setupAdapter1(type, search);
        mPostRecyclerView.setAdapter(mPostAdapter);
    }

    public void setupAdapter1(String type, String search) {

//        mAuth.addAuthStateListener(mAuthListener);
        Query query = null;
        if(type.equals("postDesc")){
            //query = FirebaseUtils.getPostRef().child(postType).orderByChild(type+"/"+type).startAt(search);
            query = FirebaseUtils.getPostRef().child(postType).orderByChild(type).startAt(search).endAt(search+"\uf8ff");
        }else if(type.equals("postTitle")){
            query = FirebaseUtils.getPostRef().child(postType).orderByChild(type).startAt(search).endAt(search+"\uf8ff");
        }
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.row_board,
                PostViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, final Post model, final int position) {

                viewHolder.setNumComments(String.valueOf(model.getNumComments()));
                viewHolder.setTime(DateUtils.getRelativeTimeSpanString(model.getTimeCreated()));
                viewHolder.setUsername(model.getUser().getUser());
                viewHolder.setPostTitle(model.getPostTitle());

                Glide.with(getActivity())
                        .load(model.getUser().getImageUrl())
                        .into(viewHolder.postImage);

                if (model.getPostImageUrl() != null) {
                    viewHolder.postImage.setVisibility(View.VISIBLE);
                    viewHolder.setImage(getActivity(),model.getPostImageUrl());
                } else {
                    viewHolder.postImage.setImageBitmap(null);
                    viewHolder.postImage.setVisibility(View.GONE);
                }


                viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), PostActivity.class);
                        intent.putExtra("postID", model.getPostId());
                        intent.putExtra("postType", model.getPostType());
                        startActivity(intent);
                    }
                });

            }
        };

        if(query==null){
            Toast.makeText(getActivity(), "결과 없음", Toast.LENGTH_SHORT).show();
        }

    }

    public void setupAdapter(String str) {

//        mAuth.addAuthStateListener(mAuthListener);

        //Query query = FirebaseUtils.getPostRef().orderByChild("timeCreated");
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.row_board,
                PostViewHolder.class,
                FirebaseUtils.getPostRef().child(str)
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, final Post model, final int position) {

                viewHolder.setNumComments(String.valueOf(model.getNumComments()));
                viewHolder.setTime(DateUtils.getRelativeTimeSpanString(model.getTimeCreated()));
                viewHolder.setUsername(model.getUser().getUser());
                viewHolder.setPostTitle(model.getPostTitle());

                Glide.with(getActivity())
                        .load(model.getUser().getImageUrl())
                        .into(viewHolder.postImage);

                if (model.getPostImageUrl() != null) {
                    viewHolder.postImage.setVisibility(View.VISIBLE);
                    viewHolder.setImage(getActivity(),model.getPostImageUrl());
                } else {
                    viewHolder.postImage.setImageBitmap(null);
                    viewHolder.postImage.setVisibility(View.GONE);
                }


                viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), PostActivity.class);
                        intent.putExtra("postID", model.getPostId());
                        intent.putExtra("postType", model.getPostType());
                        startActivity(intent);
                    }
                });

            }
        };

    }


    public static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView postTitle;
        ImageView postImage;
        TextView postUser;
        TextView postTime;
        TextView postNumComments;
        LinearLayout linearLayout;

        public PostViewHolder(View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.board_title);
            postImage = (ImageView) itemView.findViewById(R.id.board_img);
            postUser = (TextView) itemView.findViewById(R.id.board_name);
            postTime = (TextView) itemView.findViewById(R.id.board_time);
            postNumComments = (TextView) itemView.findViewById(R.id.board_comment);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.board_layout);
        }

        public void setPostTitle(String title){postTitle.setText(title);}

        public void setUsername(String username) {
            postUser.setText(username);
        }

        public void setTime(CharSequence time) {
            postTime.setText(time);
        }

        public void setNumComments(String numComments) {
            postNumComments.setText(numComments);
        }

        public void setImage(Context context, String image){
            Picasso.with(context).load(image).into(postImage);
        }

    }

}

