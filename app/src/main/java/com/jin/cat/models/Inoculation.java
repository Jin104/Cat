package com.jin.cat.models;

/**
 * Created by rakha on 2017-11-26.
 */

public class Inoculation {

    private String date;
    private String key;

    public Inoculation(){}

    public Inoculation(String date, String key) {
        this.date = date;
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
