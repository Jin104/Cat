package com.jin.cat.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jin.cat.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    Context con;
    String[] data;

    public ViewPagerAdapter(Context con, String[] data) {
        this.data = data;
        this.con = con;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_item, container, false);
        try {

            ImageView imageView = (ImageView) view.findViewById(R.id.pagerItem);
            Picasso.with(imageView.getContext()).load(data[position]).into(imageView);
            //imageView.setImageResource(data[position]);
            ((ViewPager) container).addView(view);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
