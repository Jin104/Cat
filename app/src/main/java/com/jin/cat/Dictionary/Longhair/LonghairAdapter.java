package com.jin.cat.Dictionary.Longhair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jin.cat.Dictionary.Cat;
import com.jin.cat.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by rakha on 2017-11-06.
 */

public class LonghairAdapter extends RecyclerView.Adapter<LonghairAdapter.LonghairViewHolder>{



    private final String TAG = LonghairAdapter.LonghairViewHolder.class.getSimpleName();

    private List<Cat> lists;

    public LonghairAdapter(List<Cat> lists) {
        this.lists = lists;
    }

    @Override
    public LonghairViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LonghairViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dictionary_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(final LonghairAdapter.LonghairViewHolder holder, final int position) {

        Cat cat = lists.get(position);

        Context context = holder.listCover.getContext();
        holder.setImage(context, cat.getImage());
        holder.listTitle.setText(cat.getName());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "test1");
                menu.add(holder.getAdapterPosition(), 0, 0, "test2");
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class LonghairViewHolder extends RecyclerView.ViewHolder{

        public TextView listTitle;
        public ImageView listCover;


        public LonghairViewHolder(View itemView) {
            super(itemView);

            listTitle = (TextView) itemView.findViewById(R.id.cat_list_title);
            listCover = (ImageView) itemView.findViewById(R.id.cat_list_image);
        }

        public void setImage(Context context, String image) {
            Picasso.with(context).load(image).into(listCover);
        }

        public void setTitle(String title){
            listTitle.setText(title);
        }
    }
}



