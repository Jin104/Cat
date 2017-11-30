package com.jin.cat.adapter;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.jin.cat.R;
import com.jin.cat.models.Disease;
import com.jin.cat.models.ExpandableList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-11-29.
 */

/*
public class DiseaseAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> diseaseTitle;
    private HashMap<String, List<String>> tv_cause1;

    public DiseaseAdapter(Context context, List<String> diseaseTitle,HashMap<String, List<String>> tv_cause1) {
        this.context = context;
        this.diseaseTitle = diseaseTitle;
        this.tv_cause1 = tv_cause1;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.tv_cause1.get(this.tv_cause1.get(listPosition)).get(expandedListPosition);
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
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.tv_cause1);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.tv_cause1.get(this.diseaseTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.diseaseTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.diseaseTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String diseaseTitle = (String) getGroup(listPosition);

        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.diseaseTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(diseaseTitle);
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



class MyViewHolderWithChild extends RecyclerView.ViewHolder {

    public TextView tv_parent, tv_cause1, tv_cause2, tv_cure1, tv_cure2;
    public RelativeLayout button;
    public ExpandableLinearLayout expandLayout;

    public MyViewHolderWithChild(View itemView) {
        super(itemView);
        tv_parent = (TextView)itemView.findViewById(R.id.tv_parent);
        tv_cause1 = (TextView)itemView.findViewById(R.id.tv_cause1);
        tv_cause2 = (TextView)itemView.findViewById(R.id.tv_cause2);
        tv_cure1 = (TextView)itemView.findViewById(R.id.tv_cure1);
        tv_cure2 = (TextView)itemView.findViewById(R.id.tv_cure2);
        button = (RelativeLayout)itemView.findViewById(R.id.button);
        expandLayout = (ExpandableLinearLayout)itemView.findViewById(R.id.expandLayout);
    }
}

public class DiseaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Disease>items;
    //List<ExpandableList>items;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public DiseaseAdapter(List<Disease>items) {
        this.items = items;
        for(int i=0; i<items.size(); i++)
            expandState.append(i, false);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.disease_child, parent, false);
        return new MyViewHolderWithChild(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild)holder;
        Disease item = items.get(position);
        viewHolder.setIsRecyclable(false);
        viewHolder.tv_parent.setText(item.getTitle());

        viewHolder.expandLayout.setInRecyclerView(true);
        viewHolder.expandLayout.setExpanded(expandState.get(position));
        viewHolder.expandLayout.setListener(new ExpandableLayoutListenerAdapter() {

            @Override
            public void onPreOpen() {
                changeRotate(viewHolder.button, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                changeRotate(viewHolder.button, 180f, 0f).start();
                expandState.put(position, false);
            }

        });

        viewHolder.button.setRotation(expandState.get(position)?180f:0f);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.expandLayout.toggle();
            }
        });
        viewHolder.tv_cause1.setText(items.get(position).getDesc1());
        viewHolder.tv_cause2.setText(items.get(position).getDesc2());
        viewHolder.tv_cure1.setText(items.get(position).getDesc3());
        viewHolder.tv_cure2.setText(items.get(position).getDesc4());
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

