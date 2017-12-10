package com.jin.cat.Knowledge.Food;

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
 * Created by inhye on 2017-11-08.
 */


/*
 *
 * Changed
 * This class is not used.
 *
 */
public class FoodAdapter extends ArrayAdapter<String> {

    String[] names;
    int[] flags;
    Context mContext;

    public FoodAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super(context, R.layout.row_language);
        this.names = countryNames;
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
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_language, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.languageImage);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.languageName);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(flags[position]);
        mViewHolder.mName.setText(names[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }
}
