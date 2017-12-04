package com.jin.cat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.R;

/**
 * Created by Jin on 2017-12-04.
 */

public class CatList1Acdapter extends ArrayAdapter<String> {

    private String[] names;
    private String[] count;
    private int[] flags;
    private Context mContext;

    public CatList1Acdapter(Context context, String[] countryNames, String[] counts, int[] countryFlags) {
        super(context, R.layout.row_sex);
        this.names = countryNames;
        this.count=counts;
        this.flags = countryFlags;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CatList1Acdapter.ViewHolder mViewHolder = new CatList1Acdapter.ViewHolder();
        if(convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_dictionary_cat, parent, false);
            mViewHolder.mFlag = (ImageView)convertView.findViewById(R.id.cat_list_image);
            mViewHolder.mName = (TextView)convertView.findViewById(R.id.cat_list_title);
            mViewHolder.mCount = (TextView)convertView.findViewById(R.id.cat_list_title_eng);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (CatList1Acdapter.ViewHolder)convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(flags[position]);
        mViewHolder.mName.setText(names[position]);
        mViewHolder.mCount.setText(count[position]);

        return convertView;
    }
    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
        TextView mCount;
    }
}
