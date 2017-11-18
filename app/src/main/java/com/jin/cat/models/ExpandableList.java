package com.jin.cat.models;

/**
 * Created by sunmoon on 2017-11-17.
 */

public class ExpandableList {

    private  String title, desc;
    private boolean isExpandable;
    private String key;

    public ExpandableList(){this.isExpandable=true;}

    public ExpandableList(String title, String desc, boolean isExpandable, String key) {
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
}
