package com.jin.cat.Dictionary.Model;

/**
 * Created by rakha on 2017-11-07.
 */

public class Cat {

    private String key;
    private String hair;
    private String name;
    private String image;
    private String country;
    private String origin;
    private String looks;
    private String personality;
    private String manage;

    public Cat(){}

    public Cat(String key, String hair, String name, String image, String country, String origin, String looks, String personality, String manage) {
        this.key = key;
        this.hair = hair;
        this.name = name;
        this.image = image;
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
