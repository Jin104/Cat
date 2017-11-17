package com.jin.cat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.R;
import com.jin.cat.models.Language;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rakha on 2017-11-06.
 */

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.LanguageViewHolder>{

    private final String TAG = LanguageListAdapter.LanguageViewHolder.class.getSimpleName();

    private List<Language> lists;

    public LanguageListAdapter(List<Language> lists) {
        this.lists = lists;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LanguageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_language_content, parent, false));
    }

    @Override
    public void onBindViewHolder(final LanguageListAdapter.LanguageViewHolder holder, final int position) {

        final Language language = lists.get(position);

        final Context context = holder.listCover.getContext();
        holder.setImage(context, language.getImage());
        holder.listTitle.setText(language.getTitle());
        holder.listDesc.setText(language.getDesc());


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class LanguageViewHolder extends RecyclerView.ViewHolder{

        public TextView listTitle;
        public TextView listDesc;
        public ImageView listCover;


        public LanguageViewHolder(View itemView) {
            super(itemView);

            listTitle = (TextView) itemView.findViewById(R.id.languageTitle);
            listDesc = (TextView) itemView.findViewById(R.id.languageDesc);
            listCover = (ImageView) itemView.findViewById(R.id.languageImage);
        }

        public void setImage(Context context, String image) {
            Picasso.with(context).load(image).into(listCover);
        }

        public void setTitle(String title){
            listTitle.setText(title);
        }

        public void setDesc(String title){listDesc.setText(title);}
    }
}



