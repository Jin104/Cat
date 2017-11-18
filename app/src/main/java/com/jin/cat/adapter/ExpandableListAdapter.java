package com.jin.cat.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.jin.cat.interfaces.ItemClickListener;
import com.jin.cat.models.ExpandableList;
import com.jin.cat.R;

import java.util.List;

/**
 * Created by sunmoon on 2017-11-17.
 */

class ExpandableListViewHolderWithoutChild extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;

    //Toast
    ItemClickListener itemClickListener;

    public ExpandableListViewHolderWithoutChild(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(this);
    }

    //Toast
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

class ExpandableListViewHolderWithChild extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView, textViewChild;
    public RelativeLayout childButton;
    public ExpandableLinearLayout expandableLayout;

    ItemClickListener itemClickListener;

    public ExpandableListViewHolderWithChild(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);
        textViewChild = (TextView)itemView.findViewById(R.id.textViewChild);
        childButton = (RelativeLayout)itemView.findViewById(R.id.childButton);
        expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);

        //Toast
        itemView.setOnClickListener(this);
    }

    //Toast
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //Toast
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ExpandableList> items;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public ExpandableListAdapter(List<ExpandableList> items) {
        this.items = items;
        for(int i=0; i<items.size(); i++)
            expandState.append(i, false);
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).isExpandable())
            return 1;
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if(viewType==0) { //without item
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.expandableList_without_child, parent, false);
            return new ExpandableListViewHolderWithoutChild(view);
        }
        else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.expandableList_with_child, parent, false);
            return new ExpandableListViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
            {
                ExpandableListViewHolderWithoutChild viewHolder = (ExpandableListViewHolderWithoutChild)holder;
                ExpandableList item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getTitle());

                //Toast, Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "Without child click : "+items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
            case 1:
            {
                final ExpandableListViewHolderWithChild viewHolder = (ExpandableListViewHolderWithChild)holder;
                ExpandableList item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getTitle());

                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.childButton,0f,180f).start();
                        expandState.put(position, true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.childButton,180f,0f).start();
                        expandState.put(position, true);
                    }
                });

                viewHolder.childButton.setRotation(expandState.get(position)?180f:0f);

                //Toast
                viewHolder.childButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.expandableLayout.toggle();
                    }
                });

                //Toast
                viewHolder.textViewChild.setText(items.get(position).getDesc());
                viewHolder.textViewChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, ""+items.get(position).getDesc(), Toast.LENGTH_SHORT).show();
                    }
                });

                //Toast, Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "With child click : "+items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(RelativeLayout childButton, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(childButton, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
