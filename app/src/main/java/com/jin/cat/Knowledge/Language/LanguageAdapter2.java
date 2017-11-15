package com.jin.cat.Knowledge.Language;

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
 * Created by inhye on 2017-10-25.
 */

public class LanguageAdapter2 extends ArrayAdapter<String> {

    private String[] names;
    private String[] imfo;
    private int[] flags;
    private Context mContext;

    public LanguageAdapter2(Context context, String[] countryNames,String[] countryImfo, int[] countryFlags) {
        super(context, R.layout.language_list2_type);
        this.names = countryNames;
        this.imfo = countryImfo;
        this.flags = countryFlags;
        this.mContext = context;
    }

    @Override
    public int getCount()
    {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.language_list2_type, parent, false);

            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.tvState);
            mViewHolder.mImfo = (TextView) convertView.findViewById(R.id.tvImfo);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(flags[position]);
        mViewHolder.mName.setText(names[position]);
        mViewHolder.mImfo.setText(imfo[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
        TextView mImfo;
    }
}
