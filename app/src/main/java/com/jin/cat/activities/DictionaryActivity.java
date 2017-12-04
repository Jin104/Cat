package com.jin.cat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jin.cat.R;
import com.jin.cat.fragments.DictionaryFragment;
import com.jin.cat.models.Dictionary;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class DictionaryActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        RecyclerView dictionlistRecyclerView = (RecyclerView)findViewById(R.id.dictionary_list_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        dictionlistRecyclerView.setLayoutManager(linearLayout);
        dictionlistRecyclerView.setHasFixedSize(true);
    }


    private List<Dictionary> getDictionaryData(){
        List<Dictionary> dictionList = new ArrayList<Dictionary>();
        dictionList.add(new Dictionary(R.drawable.dictionary_cat3,"장모종","17 cats"));
        dictionList.add(new Dictionary(R.drawable.dictionary_cat2,"중모종", "5 cats"));
        dictionList.add(new Dictionary(R.drawable.dictionary_cat1,"단모종", "32 cats"));
        return dictionList;
    }

    public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryFragment.DictionaryViewHolder>{

        private final String TAG = DictionaryFragment.DictionaryAdapter.class.getSimpleName();

        private Context context;
        private List<Dictionary> dictionLists;

        public DictionaryAdapter(Context context, List<Dictionary> dictionLists) {
            this.context = context;
            this.dictionLists = dictionLists;
        }


        public DictionaryAdapter(ViewGroup parent, int viewType) {
            setContentView(R.layout.row_dictionary);
        }


        @Override
        public DictionaryFragment.DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(DictionaryFragment.DictionaryViewHolder holder, final int position) {

            final Dictionary dictionlistObject = dictionLists.get(position);
            holder.dictionListCover.setImageResource(dictionlistObject.getDictionaryImage());
            holder.dictionListTitle.setText(dictionlistObject.getDictionaryTitle());
            holder.dictionListCount.setText(dictionlistObject.getDictionaryCount());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (valueOf(position)){

                        case 0:
                            intent.putExtra("contentId", "Long");
                            intent.putExtra("title","장모종");
                            break;

                        case 1:
                            intent.putExtra("contentId", "Middle");
                            intent.putExtra("title","중모종");
                            break;

                        case 2:
                            intent.putExtra("contentId", "Short");
                            intent.putExtra("title","단모종");
                            break;
                    }

                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return dictionLists.size();
        }
    }
}
