package com.jin.cat.Knowledge.DoNotEat;

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

import org.w3c.dom.Text;

/**
 * Created by sunmoon on 2017-11-17.
 */

public class DoNotEatAdapter extends ArrayAdapter<String> {

    private String[] names;
    private int[] flags;
    private Context mContext;

    public DoNotEatAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super(context, R.layout.do_not_eat_list);
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
        DoNotEatAdapter.ViewHolder mViewHolder = new DoNotEatAdapter.ViewHolder();
        if(convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.do_not_eat_list, parent, false);
            mViewHolder.mFlag = (ImageView)convertView.findViewById(R.id.donotEatImage);
            mViewHolder.mName = (TextView)convertView.findViewById(R.id.donotEatName);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (DoNotEatAdapter.ViewHolder)convertView.getTag();
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
