package com.jin.cat.Knowledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.R;

import java.util.List;

/**
 * Created by rakha on 2017-10-21.
 */


public class KnowledgeAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<ItemObject> listStorage;
    private Context context;

    public KnowledgeAdapter(Context context, List<ItemObject> customizedListView) {
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
            convertView = layoutinflater.inflate(R.layout.knowledge_list, parent, false);
            listViewHolder.knowledgeImage = (ImageView)convertView.findViewById(R.id.knowledge_image);
            listViewHolder.knowledgeName = (TextView)convertView.findViewById(R.id.knowledge_name);
            listViewHolder.knowledgeDetail = (TextView)convertView.findViewById(R.id.knowledge_detail);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.knowledgeImage.setImageResource(listStorage.get(position).getKnowledgeImage());
        listViewHolder.knowledgeName.setText(listStorage.get(position).getKnowledgeName());
        listViewHolder.knowledgeDetail.setText(listStorage.get(position).getKnowledgeDetail());

        return convertView;
    }

    static class ViewHolder{
        ImageView knowledgeImage;
        TextView knowledgeName;
        TextView knowledgeDetail;
    }

}
