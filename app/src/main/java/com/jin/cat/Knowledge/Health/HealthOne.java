package com.jin.cat.Knowledge.Health;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.jin.cat.Knowledge.Health.Adapter.MyAdapter;
import com.jin.cat.Knowledge.Health.Model.TitleChild;
import com.jin.cat.Knowledge.Health.Model.TitleCreator;
import com.jin.cat.Knowledge.Health.Model.TitleParent;
import com.jin.cat.R;

import java.util.ArrayList;
import java.util.List;

public class HealthOne extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_one);

        recyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this,initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);
    }

    private List<ParentObject> initData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        List<TitleParent> titles = titleCreator.getAll();
        List<ParentObject> parentObject = new ArrayList<>();
        for(TitleParent title:titles)
        {
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleChild("Add to contacts","Send message")); //여기가 아래 누르면 뜨는곳
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;
    }
}
