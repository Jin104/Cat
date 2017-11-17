package com.jin.cat.Knowledge.DoNotEat.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.jin.cat.Knowledge.DoNotEat.Interface.ItemClickListener;
import com.jin.cat.Knowledge.DoNotEat.Model.Item;
import com.jin.cat.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sunmoon on 2017-11-17.
 */

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;

    ItemClickListener itemClickListener;

    public MyViewHolderWithoutChild(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView, textViewChild;
    public RelativeLayout childButton;
    public ExpandableLinearLayout expandableLayout;

    ItemClickListener itemClickListener;

    public MyViewHolderWithChild(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);
        textViewChild = (TextView)itemView.findViewById(R.id.textViewChild);
        childButton = (RelativeLayout)itemView.findViewById(R.id.childButton);
        expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> items;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapter(List<Item> items) {
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
            View view = inflater.inflate(R.layout.do_not_eat_without_child, parent, false);
            return new MyViewHolderWithoutChild(view);
        }
        else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.do_not_eat_with_child, parent, false);
            return new MyViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
            {
                MyViewHolderWithoutChild viewHolder = (MyViewHolderWithoutChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getText());

                //Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "Without child click : "+items.get(position).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
            case 1:
            {
                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getText());

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
                viewHolder.childButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.expandableLayout.toggle();
                    }
                });

                viewHolder.textViewChild.setText(items.get(position).getSubText());
                viewHolder.textViewChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, ""+items.get(position).getSubText(), Toast.LENGTH_SHORT).show();
                    }
                });

                //Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "With child click : "+items.get(position).getText(), Toast.LENGTH_SHORT).show();
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
