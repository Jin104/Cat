package com.jin.cat.models;

/**
 * Created by rakha on 2017-11-17.
 */

public class Language {

    private String key;
    private String image;
    private String title;
    private String desc;

    public Language (){}

    public Language(String key, String image, String title, String desc) {
        this.key = key;
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
