package com.jin.cat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.jin.cat.R;
import com.jin.cat.models.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmoon on 2017-12-06.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private List<Goods> goodsItem;
    private Context context;

    public GoodsAdapter(List<Goods> goodsItem, Context context) {
        this.goodsItem = goodsItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_goods, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goods goods = goodsItem.get(position);

        holder.goodsHead.setText(goods.getHead());
        holder.goodsDesc.setText(goods.getDesc());
    }

    @Override
    public int getItemCount() {
        return goodsItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView goodsHead;
        public TextView goodsDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            goodsHead = (TextView)itemView.findViewById(R.id.goodsHead);
            goodsDesc = (TextView)itemView.findViewById(R.id.goodsDesc);
        }
    }
}
