package com.jin.cat.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.jin.cat.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-12-06.
 */


public class GoodsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> goodsTitle;
    private HashMap<String, List<String>> goodsDetail;

    public GoodsAdapter(Context context, List<String> goodsTitle,HashMap<String, List<String>> goodsDetail) {
        this.context = context;
        this.goodsTitle = goodsTitle;
        this.goodsDetail = goodsDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.goodsDetail.get(this.goodsTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_goods, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.goodsDesc);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.goodsDetail.get(this.goodsTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.goodsTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.goodsTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String listTitle = (String) getGroup(listPosition);

        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_group_goods, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.goodsHead);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
//        listTitleTextView.setTextSize(25.0f);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}

/*public class GoodsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public GoodsAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<String>> expandableListDetail) {

        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {

        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_goods, null);
        }
        TextView expandedListTextView = (TextView)convertView.findViewById(R.id.goodsDesc);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {

        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {

        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {

        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {

        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);
        if(convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_group_goods, null);
        }

        TextView listTitleTextView = (TextView)convertView.findViewById(R.id.goodsHead);

        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {

        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {

        return true;
    }
}*/


