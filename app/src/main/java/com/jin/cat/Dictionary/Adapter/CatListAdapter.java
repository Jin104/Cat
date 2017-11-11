package com.jin.cat.Dictionary.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jin.cat.Dictionary.CatDescActivity;
import com.jin.cat.Dictionary.Model.Cat;
import com.jin.cat.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rakha on 2017-11-06.
 */

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder>{



    private final String TAG = CatListAdapter.CatViewHolder.class.getSimpleName();

    private List<Cat> lists;

    public CatListAdapter(List<Cat> lists) {
        this.lists = lists;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dictionary_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(final CatListAdapter.CatViewHolder holder, final int position) {

        final Cat cat = lists.get(position);

        final Context context = holder.listCover.getContext();
        holder.setImage(context, cat.getImage());
        holder.listTitle.setText(cat.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CatDescActivity.class);
                intent.putExtra("key", cat.getKey());
                intent.putExtra("hair", cat.getHair());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder{

        public TextView listTitle;
        public ImageView listCover;


        public CatViewHolder(View itemView) {
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



