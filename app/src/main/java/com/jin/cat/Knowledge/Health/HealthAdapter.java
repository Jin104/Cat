package com.jin.cat.Knowledge.Health;

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
 * Created by sunmoon on 2017-10-28.
 */

public class HealthAdapter extends ArrayAdapter<String> {

    private String[] names;
    private int[] flags;
    private Context mContext;

    public HealthAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super(context, R.layout.health_list);
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
        HealthAdapter.ViewHolder mViewHolder = new HealthAdapter.ViewHolder();
        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.health_list, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.healthImage);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.healthName);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (HealthAdapter.ViewHolder) convertView.getTag();
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
