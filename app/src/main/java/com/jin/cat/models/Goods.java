package com.jin.cat.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-12-16.
 */

public class Goods {

    private  String title, desc;
    private boolean isExpandable;
    private String key;

    public Goods(){this.isExpandable=true;}

    public Goods(String title, String desc, boolean isExpandable, String key) {
        this.title = title;
        this.desc = desc;
        this.isExpandable = isExpandable;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public String getKey() {
        return key;
    }



    /*public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> one = new ArrayList<String>();
        one.add("1");

        expandableListDetail.put("살려줘...", one);

        return expandableListDetail;
    }*/
}
