package com.jin.cat.Knowledge.Health.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.jin.cat.Knowledge.Health.Model.TitleChild;
import com.jin.cat.Knowledge.Health.Model.TitleParent;
import com.jin.cat.Knowledge.Health.ViewHolder.TitleChildViewHolder;
import com.jin.cat.Knowledge.Health.ViewHolder.TitleParentViewHolder;
import com.jin.cat.R;

import java.util.List;

/**
 * Created by inhye on 2017-11-08.
 */

public class FirstAidAdapter extends ExpandableRecyclerAdapter<TitleParentViewHolder,TitleChildViewHolder>{

    private LayoutInflater inflater;

    public FirstAidAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.activity_health_one_parent,viewGroup,false);
        return new TitleParentViewHolder(view);
    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.activity_health_one_child,viewGroup,false);
        return new TitleChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {
        TitleParent title = (TitleParent)o;
        titleParentViewHolder._textView.setText(title.getTitle());

    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, int i, Object o) {
        TitleChild title = (TitleChild)o;
        titleChildViewHolder.option1.setText(title.getOption1());

    }
}