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
 * Created by rakha on 2017-10-22.
 */

public class LanguageAdapter extends ArrayAdapter<String> {

    String[] names;
    int[] flags;
    Context mContext;

    public LanguageAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super(context, R.layout.language_list);
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
            convertView = mInflater.inflate(R.layout.language_list, parent, false);
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