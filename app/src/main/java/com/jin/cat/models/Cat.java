package com.jin.cat.models;

/**
 * Created by rakha on 2017-11-07.
 */

public class Cat {

    private String key;
    private String hair;
    private String name;
    private String image;
    private String image1;
    private String image2;
    private String country;
    private String origin;
    private String looks;
    private String personality;
    private String manage;

    public Cat(){}

    public Cat(String key, String hair, String name, String image, String image1, String image2, String country, String origin, String looks, String personality, String manage) {
        this.key = key;
        this.hair = hair;
        this.name = name;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.country = country;
        this.origin = origin;
        this.looks = looks;
        this.personality = personality;
        this.manage = manage;
    }

    public String getKey() {
        return key;
    }

    public String getHair() {
        return hair;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getCountry() {
        return country;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLooks() {
        return looks;
    }

    public String getPersonality() {
        return personality;
    }

    public String getManage() {
        return manage;
    }
}
