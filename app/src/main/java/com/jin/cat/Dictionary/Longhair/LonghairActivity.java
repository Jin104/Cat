package com.jin.cat.Dictionary.Longhair;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Knowledge.Health.HealthActivity;
import com.jin.cat.Knowledge.Knowledge;
import com.jin.cat.Knowledge.KnowledgeFragment;
import com.jin.cat.Knowledge.Language.LanguageActivity;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class LonghairActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longhair);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.longhair_list_view);
        GridLayoutManager gridLayout = new GridLayoutManager(LonghairActivity.this, 2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setHasFixedSize(true);

        LonghairActivity.LonghairAdapter mAdapter = new LonghairActivity.LonghairAdapter(LonghairActivity.this, getLonghairData());
        recyclerView.setAdapter(mAdapter);

    }


    public List<Longhair> getLonghairData() {
        List<Longhair> longList = new ArrayList<Longhair>();
        longList.add(new Longhair("고양이 사전", R.drawable.one));
        longList.add(new Longhair("고양이 행동언어", R.drawable.two));
        longList.add(new Longhair("고양이 마사지", R.drawable.three));
        longList.add(new Longhair("고양이가 먹으면 안되는 음식", R.drawable.four));
        longList.add(new Longhair("고양이 건강", R.drawable.five));
        longList.add(new Longhair("고양이 사료", R.drawable.six));
        return longList;
    }

    public class LonghairAdapter extends RecyclerView.Adapter<LonghairActivity.LonghairViewHolder>{

        private final String TAG = LonghairActivity.LonghairViewHolder.class.getSimpleName();

        private Context context;
        private List<Longhair> knowLists;

        public LonghairAdapter(Context context, List<Longhair> knowLists) {
            this.context = context;
            this.knowLists = knowLists;
        }

        @Override
        public LonghairViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_dictionary_cat, parent, false);

            return new LonghairActivity.LonghairViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LonghairActivity.LonghairViewHolder holder, final int position) {

            final Longhair listObject = knowLists.get(position);
            holder.listCover.setImageResource(listObject.getLonghairImage());
            holder.listTitle.setText(listObject.getLonghairTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (valueOf(position)){

                        case 0:
                            break;
                        case 1:
                            startActivity(new Intent(LonghairActivity.this, LanguageActivity.class));
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            startActivity(new Intent(LonghairActivity.this, HealthActivity.class));
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        default:
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return knowLists.size();
        }
    }


    public class LonghairViewHolder extends RecyclerView.ViewHolder{

        public TextView listTitle;
        public ImageView listCover;

        public LonghairViewHolder(View itemView) {
            super(itemView);

            listTitle = (TextView)itemView.findViewById(R.id.cat_list_title);
            listCover = (ImageView)itemView.findViewById(R.id.cat_list_image);
        }
    }
}
