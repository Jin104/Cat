package com.jin.cat.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Knowledge.Food.FoodActivity;
import com.jin.cat.R;
import com.jin.cat.activities.CareActivity;
import com.jin.cat.activities.DictionaryActivity;
import com.jin.cat.activities.DiseaseActivity;
import com.jin.cat.activities.FirstAidActivity;
import com.jin.cat.activities.GoodsActivity;
import com.jin.cat.activities.LanguageActivity;
import com.jin.cat.activities.VaccinationActivity;
import com.jin.cat.adapter.KnowledgeAdapter;

/**
 * Created by rakha on 2017-10-21.
 */

public class KnowledgeFragment extends Fragment {

    private GridView mlistView;

    private String[] countryNames = {"사전", "행동언어","응급처치","사료","관리","예방접종","용품","병"};
    private int[] countryFlags = {R.drawable.dic,
            R.drawable.if_cat_paper_185526,
            R.drawable.firstaid,
            R.drawable.animal_feed,
            R.drawable.if_cat_laptop_185531,
            R.drawable.steroids,
            R.drawable.goods,
            R.drawable.virus,
            };

    public KnowledgeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

        mlistView = (GridView) view.findViewById(R.id.knowledge_list);
        KnowledgeAdapter mAdapter = new KnowledgeAdapter(getActivity(), countryNames, countryFlags);
        GridLayoutManager linearLayout = new GridLayoutManager(getActivity(), 2);

        mlistView.setAdapter(mAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    //사전
                    case 0:
                        startActivity(new Intent(getActivity(), DictionaryActivity.class));
                        break;

                    //언어
                    case 1:
                        startActivity(new Intent(getActivity(), LanguageActivity.class));
                        break;

                    //응급처치
                    case 2:
                        Intent intent1 = new Intent(getActivity(), FirstAidActivity.class);
                        intent1.putExtra("contentId", "응급처치");
                        intent1.putExtra("title", "응급처치");
                        startActivity(intent1);
                        break;

                    //사료
                    case 3:
                        startActivity(new Intent(getActivity(), FoodActivity.class));
                        break;

                    //관리
                    case 4:
                        startActivity(new Intent(getActivity(), CareActivity.class));
                        break;

                    //예방접종
                    case 5:
                        startActivity(new Intent(getActivity(), VaccinationActivity.class));
                        break;

                    //용품
                    case 6:
                        startActivity(new Intent(getActivity(), GoodsActivity.class));
                        break;

                    //병
                    case 7:
                        Intent intent2 = new Intent(getActivity(), DiseaseActivity.class);
                        intent2.putExtra("contentId", "병");
                        intent2.putExtra("title", "병");
                        startActivity(intent2);
                        break;

                    default:
                        break;
                }
            }
        });
        return view;
    }

    public class KnowledgeViewHolder extends RecyclerView.ViewHolder {

        public TextView knowListTitle;
        public ImageView knowListCover;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);

            Typeface typeFace1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothic.otf");

            knowListTitle = (TextView) itemView.findViewById(R.id.knowledge_list_title);
            knowListCover = (ImageView) itemView.findViewById(R.id.knowledge_list_image);

            knowListTitle.setTypeface(typeFace1);
        }
    }
}