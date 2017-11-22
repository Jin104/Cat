package com.jin.cat.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private int width = 512;
    private int height = 512;

    private String[] names;
    private int[] flags;
    private Context mContext;

    public LanguageAdapter(Context context, String[] countryNames, int[] countryFlags) {
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

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), flags[position], options);
        options.inSampleSize = setSimpleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), flags[position], options);


        mViewHolder.mFlag.setImageBitmap(bitmap);
        mViewHolder.mName.setText(names[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){

        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        int size = 1;

        while(requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
    }
}
