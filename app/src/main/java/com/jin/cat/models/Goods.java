package com.jin.cat.models;

import java.util.ArrayList;

/**
 * Created by sunmoon on 2017-12-06.
 */

public class Goods {

    private String head;
    private String desc;

    public Goods(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
