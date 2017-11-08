package com.jin.cat.Dictionary.Model;

/**
 * Created by rakha on 2017-11-07.
 */

public class Cat {

    private String key;
    private String name;
    private String image;
    private String country;
    private String origin;
    private String looks;
    private String personality;

    public Cat(){}

    public Cat(String key, String name, String image, String country, String origin, String looks, String personality) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.country = country;
        this.origin = origin;
        this.looks = looks;
        this.personality = personality;
    }

    public String getKey() {
        return key;
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
}
