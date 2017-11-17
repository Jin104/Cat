package com.jin.cat.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jin.cat.Knowledge.DoNotEat.DoNotEatActivity;
import com.jin.cat.Knowledge.Food.FoodActivity;
import com.jin.cat.Knowledge.Health.HealthActivity;
import com.jin.cat.activities.LanguageActivity;
import com.jin.cat.Knowledge.Massage.MassageActivity;
import com.jin.cat.R;
import com.jin.cat.models.Knowledge;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

/**
 * Created by rakha on 2017-10-21.
 */

public class KnowledgeFragment extends Fragment {



    public KnowledgeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

        RecyclerView knowlistRecyclerView = (RecyclerView)view.findViewById(R.id.knowledge_list_view);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        knowlistRecyclerView.setLayoutManager(gridLayout);
        knowlistRecyclerView.setHasFixedSize(true);

        KnowledgeAdapter mAdapter = new KnowledgeAdapter(getActivity(), getKnowledgeData());
        knowlistRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public List<Knowledge> getKnowledgeData() {
        List<Knowledge> knowList = new ArrayList<Knowledge>();
        knowList.add(new Knowledge("사전", "고양이 사전", R.drawable.cat1));
        knowList.add(new Knowledge("행동언어", "고양이 행동언어", R.drawable.cat2));
        knowList.add(new Knowledge("마사지", "고양이 마사지", R.drawable.cat3));
        knowList.add(new Knowledge("먹으면안되는 음식", "고양이가 먹으면 안되는 음식", R.drawable.cat4));
        knowList.add(new Knowledge("건강", "고양이 건강", R.drawable.cat5));
        knowList.add(new Knowledge("사료", "고양이 사료", R.drawable.cat6));
        return knowList;
    }

    public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeViewHolder>{

        private final String TAG = KnowledgeAdapter.class.getSimpleName();

        private Context context;
        private List<Knowledge> knowLists;

        public KnowledgeAdapter(Context context, List<Knowledge> knowLists) {
            this.context = context;
            this.knowLists = knowLists;
        }

        @Override
        public KnowledgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_knowledge, parent, false);

            return new KnowledgeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(KnowledgeViewHolder holder, final int position) {

            final Knowledge playlistObject = knowLists.get(position);
            holder.knowListCover.setImageResource(playlistObject.getKnowledgeImage());
            holder.knowListTitle.setText(playlistObject.getKnowledgeTitle());
            holder.knowListDesc.setText(playlistObject.getKnowledgeDesc());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (valueOf(position)){

                        case 0:
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), LanguageActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), MassageActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getActivity(), DoNotEatActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getActivity(), HealthActivity.class));
                            break;
                        case 5:
                            startActivity(new Intent(getActivity(), FoodActivity.class));
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


    public class KnowledgeViewHolder extends RecyclerView.ViewHolder{

        public TextView knowListTitle;
        public TextView knowListDesc;
        public ImageView knowListCover;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);

            Typeface typeFace1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothicBold.otf");
            Typeface typeFace2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothic.otf");

            knowListTitle = (TextView)itemView.findViewById(R.id.knowledge_list_title);
            knowListDesc = (TextView)itemView.findViewById(R.id.knowledge_list_desc);
            knowListCover = (ImageView)itemView.findViewById(R.id.knowledge_list_image);

            knowListTitle.setTypeface(typeFace1);
            knowListDesc.setTypeface(typeFace2);
        }
    }


}
