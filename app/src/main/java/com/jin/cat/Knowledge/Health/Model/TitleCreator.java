package com.jin.cat.Knowledge.Health.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inhye on 2017-11-08.
 */

public class TitleCreator {
    static TitleCreator _titleCreator;
    List<TitleParent> _titleParents;

    public TitleCreator(Context context) {
        _titleParents = new ArrayList<>();
//        for (int i=1; i<=100;i++)
//        {
//            TitleParent title = new TitleParent(String.format("Caller #%d",i));
//            _titleParents.add(title);
//        }

        TitleParent title = new TitleParent(String.format("응급처치1"));
            _titleParents.add(title);
    }

    public static TitleCreator get(Context context)
    {
        if (_titleCreator == null)
            _titleCreator = new TitleCreator(context);
        return _titleCreator;
    }

    public List<TitleParent> getAll() {
        return _titleParents;
    }
}
