package com.jin.cat.models;

/**
 * Created by rakha on 2017-11-24.
 */

public class MyCat {

    private String key;
    private String name;
    private String image;
    private String sex;
    private String type;
    private String year;
    private String month;
    private String day;
    private String uid;

    public MyCat(){}

    public MyCat(String key, String name, String image, String sex, String type, String year, String month, String day, String uid) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.sex = sex;
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }





}
