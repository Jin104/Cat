package com.jin.cat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jin.cat.R;
import com.jin.cat.activities.CatDescActivity;
import com.jin.cat.activities.PostActivity;
import com.jin.cat.models.Cat;
import com.jin.cat.models.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rakha on 2017-11-06.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder>{

    private final String TAG = PostListAdapter.PostListViewHolder.class.getSimpleName();

    private List<Post> lists;

    public PostListAdapter(List<Post> lists) {
        this.lists = lists;
    }

    @Override
    public PostListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PostListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_board, parent, false));
    }

    @Override
    public void onBindViewHolder(final PostListAdapter.PostListViewHolder holder, final int position) {

        final Post post = lists.get(position);

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
        return lists.size();
    }

    public static class PostListViewHolder extends RecyclerView.ViewHolder{

        public TextView postTitle;
        public ImageView postImage;
        public TextView postUser;
        public TextView postTime;
        public TextView postNumComments;
        public LinearLayout linearLayout;


        public PostListViewHolder(View itemView) {
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



