package com.jin.cat.Dictionary.Shorthair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Dictionary.Cat;
import com.jin.cat.Dictionary.Longhair.LonghairAdapter;
import com.jin.cat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rakha on 2017-11-07.
 */

public class ShorthairAdapter extends RecyclerView.Adapter<ShorthairAdapter.ShorthairViewHolder> {

    private final String TAG = ShorthairAdapter.ShorthairViewHolder.class.getSimpleName();

    private List<Cat> lists;

    public ShorthairAdapter(List<Cat> lists) {
        this.lists = lists;
    }

    @Override
    public ShorthairAdapter.ShorthairViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ShorthairAdapter.ShorthairViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dictionary_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(final ShorthairAdapter.ShorthairViewHolder holder, final int position) {

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

    public static class ShorthairViewHolder extends RecyclerView.ViewHolder {

        public TextView listTitle;
        public ImageView listCover;


        public ShorthairViewHolder(View itemView) {
            super(itemView);

            listTitle = (TextView) itemView.findViewById(R.id.cat_list_title);
            listCover = (ImageView) itemView.findViewById(R.id.cat_list_image);
        }

        public void setImage(Context context, String image) {
            Picasso.with(context).load(image).into(listCover);
        }

        public void setTitle(String title) {
            listTitle.setText(title);
        }
    }
}