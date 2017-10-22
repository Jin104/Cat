package com.jin.cat.Dictionary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Dictionary.DictionaryAdapter;
import com.jin.cat.R;

import java.util.List;

public class DictionaryAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<DictionaryItem> listStorage;
    private Context context;

    public DictionaryAdapter(Context context, List<DictionaryItem> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.dictionary_list, parent, false);
            listViewHolder.dictionaryImage = (ImageView)convertView.findViewById(R.id.dictionaryImage);
            listViewHolder.dictionaryName = (TextView)convertView.findViewById(R.id.dictionaryName);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.dictionaryImage.setImageResource(listStorage.get(position).getDictionaryImage());
        listViewHolder.dictionaryName.setText(listStorage.get(position).getDictionaryName());

        return convertView;
    }

    static class ViewHolder{
        ImageView dictionaryImage;
        TextView dictionaryName;
    }

}
