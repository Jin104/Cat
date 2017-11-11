package com.jin.cat.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Dictionary.Model.Dictionary;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static java.lang.Integer.valueOf;

/**
 * Created by rakha on 2017-10-21.
 */

public class DictionaryFragment extends Fragment {

    public DictionaryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        RecyclerView dictionlistRecyclerView = (RecyclerView)view.findViewById(R.id.dictionary_list_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        dictionlistRecyclerView.setLayoutManager(linearLayout);
        dictionlistRecyclerView.setHasFixedSize(true);

        DictionaryAdapter mAdapter = new DictionaryAdapter(getActivity(), getDictionaryData());
        dictionlistRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private List<Dictionary> getDictionaryData(){
        List<Dictionary> dictionList = new ArrayList<Dictionary>();
        dictionList.add(new Dictionary(R.drawable.dictionary_cat3,"장모종","20 cats"));
        dictionList.add(new Dictionary(R.drawable.dictionary_cat2,"중모종", "15 cats"));
        dictionList.add(new Dictionary(R.drawable.dictionary_cat1,"단모종", "10 cats"));
        return dictionList;
    }

    public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryViewHolder>{

        private final String TAG = DictionaryFragment.DictionaryAdapter.class.getSimpleName();

        private Context context;
        private List<Dictionary> dictionLists;

        public DictionaryAdapter(Context context, List<Dictionary> dictionLists) {
            this.context = context;
            this.dictionLists = dictionLists;
        }

        @Override
        public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_dictionary, parent, false);

            return new DictionaryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DictionaryViewHolder holder, final int position) {

            final Dictionary dictionlistObject = dictionLists.get(position);
            holder.dictionListCover.setImageResource(dictionlistObject.getDictionaryImage());
            holder.dictionListTitle.setText(dictionlistObject.getDictionaryTitle());
            holder.dictionListCount.setText(dictionlistObject.getDictionaryCount());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (valueOf(position)){

                        case 0:
                            startActivity(new Intent(getActivity(), LonghairActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), MiddlehairActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), ShorthairActivity.class));
                            break;
                        default:
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return dictionLists.size();
        }
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder{

        public TextView dictionListTitle;
        public ImageView dictionListCover;
        public TextView dictionListCount;

        public DictionaryViewHolder(View itemView) {
            super(itemView);

            Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothicBold.otf");

            dictionListTitle = (TextView)itemView.findViewById(R.id.dictionary_title);
            dictionListCover = (ImageView)itemView.findViewById(R.id.dictionary_image);
            dictionListCount = (TextView)itemView.findViewById(R.id.dictionary_cat_count);

            dictionListTitle.setTypeface(typeFace);
            dictionListCount.setTypeface(typeFace);
        }
    }

}
