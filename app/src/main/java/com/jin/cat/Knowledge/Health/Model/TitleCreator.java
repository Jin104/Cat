package com.jin.cat.Knowledge.Health.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inhye on 2017-11-08.
 */

public class TitleCreator {
    private static TitleCreator _titleCreator;
    private List<TitleParent> _titleParents;

    public TitleCreator(Context context) {
        _titleParents = new ArrayList<>();


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
