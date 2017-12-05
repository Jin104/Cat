package com.jin.cat.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jin.cat.R;
import com.jin.cat.activities.PostCreateActivity;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    private RecyclerView mPostList;

    public BoardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        mPostList = (RecyclerView)view.findViewById(R.id.board_recycler);
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.board_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PostCreateActivity.class));
            }
        });
        return view;
    }

}
