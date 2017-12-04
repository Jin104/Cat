package com.jin.cat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jin.cat.Knowledge.Food.FoodActivity;
import com.jin.cat.R;
import com.jin.cat.activities.CareActivity;
import com.jin.cat.activities.DictionaryActivity;
import com.jin.cat.activities.HealthActivity;
import com.jin.cat.activities.LanguageActivity;

/**
 * Created by rakha on 2017-10-21.
 */

public class KnowledgeFragment extends Fragment {

    private LinearLayout dic;
    private LinearLayout language;
    private LinearLayout steroids;
    private LinearLayout food;
    private LinearLayout care;
    private LinearLayout health;

    public KnowledgeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

//        RecyclerView knowlistRecyclerView = (RecyclerView)view.findViewById(R.id.knowledge_list_view);
//        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
//        knowlistRecyclerView.setLayoutManager(gridLayout);
//        knowlistRecyclerView.setHasFixedSize(true);
//
//        KnowledgeAdapter mAdapter = new KnowledgeAdapter(getActivity(), getKnowledgeData());
//        knowlistRecyclerView.setAdapter(mAdapter);
        //사전
        dic = (LinearLayout) view.findViewById(R.id.dic);
        dic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DictionaryActivity.class));
                //이거를 fragment에서 activity로 해야되는데 하하하
            }
        });

        //언어
        language = (LinearLayout)view.findViewById(R.id.language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LanguageActivity.class));
            }
        });

//        //예방접종
//        steroids = (LinearLayout)view.findViewById(R.id.standard);
//        steroids.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), VaccinationActivity.class));
//            }
//        });



        //사료
        food = (LinearLayout)view.findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FoodActivity.class));
            }
        });

        //관리
        care = (LinearLayout)view.findViewById(R.id.care);
        care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CareActivity.class));
            }
        });

        //건강
        health = (LinearLayout)view.findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HealthActivity.class));
            }
        });


        return view;
    }

//    public List<Knowledge> getKnowledgeData() {
//        List<Knowledge> knowList = new ArrayList<Knowledge>();
//        knowList.add(new Knowledge("고양이몸짓의 의미", R.drawable.if_cat_paper_185526));
//        knowList.add(new Knowledge("응급처치, 예방", R.drawable.if_cat_laptop_185531));
//        knowList.add(new Knowledge("관리", R.drawable.cat4));
//        knowList.add(new Knowledge("사료", R.drawable.cat6));
//        knowList.add(new Knowledge("성", R.drawable.cat1));
//        return knowList;
//    }
//
//    public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeViewHolder>{
//
//        private final String TAG = KnowledgeAdapter.class.getSimpleName();
//
//        private Context context;
//        private List<Knowledge> knowLists;
//
//        public KnowledgeAdapter(Context context, List<Knowledge> knowLists) {
//            this.context = context;
//            this.knowLists = knowLists;
//        }

//        @Override
//        public KnowledgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(context).inflate(R.layout.row_knowledge, parent, false);
//
//            return new KnowledgeViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(KnowledgeViewHolder holder, final int position) {
//
//            final Knowledge playlistObject = knowLists.get(position);
//            holder.knowListCover.setImageResource(playlistObject.getKnowledgeImage());
//            holder.knowListTitle.setText(playlistObject.getKnowledgeTitle());
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    switch (valueOf(position)){
//
//                        case 0:
//                            startActivity(new Intent(getActivity(), LanguageActivity.class));
//                            break;
//                        case 1:
//                            startActivity(new Intent(getActivity(), HealthActivity.class));
//                            break;
//                        case 2:
//                            startActivity(new Intent(getActivity(), CareActivity.class));
//                            break;
//                        case 3:
//                            startActivity(new Intent(getActivity(), FoodActivity.class));
//                            break;
//                        case 4:
//                            startActivity(new Intent(getActivity(), SexActivity.class));
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return knowLists.size();
//        }
//    }


//    public class KnowledgeViewHolder extends RecyclerView.ViewHolder{
//
//        public TextView knowListTitle;
//        public ImageView knowListCover;
//
//        public KnowledgeViewHolder(View itemView) {
//            super(itemView);
//
//            Typeface typeFace1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothic.otf");
//
//            knowListTitle = (TextView)itemView.findViewById(R.id.knowledge_list_title);
//            knowListCover = (ImageView)itemView.findViewById(R.id.knowledge_list_image);
//
//            knowListTitle.setTypeface(typeFace1);
//        }
//    }


}
