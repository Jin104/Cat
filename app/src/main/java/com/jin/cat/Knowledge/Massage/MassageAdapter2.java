package com.jin.cat.Knowledge.Massage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.R;

import java.util.ArrayList;

/**
 * Created by sunmoon on 2017-11-05.
 */

public class MassageAdapter2 extends BaseAdapter {

    private ArrayList<MassageItem2>massageItemList = new ArrayList<MassageItem2>();

    //MassageAdapter2의 생성자
    public MassageAdapter2() {}

    //Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {
        return massageItemList.size();
    }

    //position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.massage_list2, parent, false);
        }
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.massageImage);
        TextView titleTextView = (TextView)convertView.findViewById(R.id.massageName2);

        MassageItem2 massageItem2 = massageItemList.get(position);

        iconImageView.setImageDrawable(massageItem2.getIcon());
        titleTextView.setText(massageItem2.getTitle());

        return convertView;
    }

    //지정한 위치에 있는 데이터와 관계된 아이템의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    //지정한 위치에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return massageItemList.get(position);
    }

    //아이템 데이터 추가를 위한 함수.
    public void addItem(Drawable icon, String title) {
        MassageItem2 item = new MassageItem2();

        item.setIcon(icon);
        item.setTitle(title);

        massageItemList.add(item);
    }

}
