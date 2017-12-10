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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by rakha on 2017-10-22.
 */

public class FoodAdapter extends ArrayAdapter<String> {

    private int width = 512;
    private int height = 512;

    private String[] contents;
    private String[] urls;
    private Context mContext;

    private Bitmap bitmap = null;
    private HttpURLConnection connection = null;

    private int index = 0;
    private boolean isNotEnd = false;

    public FoodAdapter(Context context, List<String> contents, List<String> urls) {
        super(context, R.layout.row_language);

        this.contents = new String[contents.size()];
        contents.toArray(this.contents);

        this.urls = new String[urls.size()];
        urls.toArray(this.urls);

        this.mContext = context;
    }

    @Override
    public int getCount() {
        return contents.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_language, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.languageImage);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.languageName);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if(position >= contents.length){
            return null;
        }

        index = position;
        isNotEnd = false;

        try {
            new Thread(){
                public void run(){
                    if(index < urls.length) {
                        try {
                            connection = (HttpURLConnection) new URL(urls[index]).openConnection();
                            connection.connect();

                            InputStream input = connection.getInputStream();

                            bitmap = BitmapFactory.decodeStream(input);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    isNotEnd = true;
                }
            }.start();

            while(!isNotEnd);

            mViewHolder.mFlag.setImageBitmap(bitmap);

            if(index < contents.length){
                mViewHolder.mName.setText(contents[index]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
