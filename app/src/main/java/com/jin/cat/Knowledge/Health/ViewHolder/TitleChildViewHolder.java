package com.jin.cat.Knowledge.Health.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.jin.cat.R;

/**
 * Created by inhye on 2017-11-08.
 */

public class TitleChildViewHolder extends ChildViewHolder{
    public TextView option1,option2;
    public TitleChildViewHolder(View itemView) {
        super(itemView);
        option1 = (TextView)itemView.findViewById(R.id.option1);
        option2 = (TextView)itemView.findViewById(R.id.option2);
    }
}
