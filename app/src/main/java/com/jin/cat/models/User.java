package com.jin.cat.models;

/**
 * Created by rakha on 2017-11-12.
 */

public class User {

    private String user;
    private String email;
    private String imageUrl;
    private String uid;

    public User() {
    }

    public User(String user, String email, String imageUrl, String uid) {

        this.user = user;
        this.email = email;
        this.imageUrl = imageUrl;
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
