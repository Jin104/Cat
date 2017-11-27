package com.jin.cat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jin.cat.R;
import com.jin.cat.models.Inoculation;

import java.util.List;

/**
 * Created by rakha on 2017-11-26.
 */

public class InoculationAdapter1 extends RecyclerView.Adapter<InoculationAdapter1.InoculationViewHolder>{

    private List<Inoculation> list;
    private Context context;

    public InoculationAdapter1(List<Inoculation> list) {
        this.list = list;
    }

    @Override
    public InoculationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new InoculationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inoculation, parent, false));
    }

    @Override
    public void onBindViewHolder(final InoculationViewHolder holder, int position) {

        final Inoculation inoculation = list.get(position);

        holder.textView.setText(inoculation.getDate());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                //context = v.getContext();
                Toast.makeText(context, "생성함", Toast.LENGTH_SHORT).show();
                menu.add(holder.getAdapterPosition(), 0, 0, "삭제하기");
                menu.add(holder.getAdapterPosition(), 1, 0, "수정하기");

            }});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class InoculationViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView textView;

        public InoculationViewHolder(View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.textView_inoculation);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Toast.makeText(context, "생성함", Toast.LENGTH_SHORT).show();
            menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "SMS");
        }
    }
}
