package com.jin.cat.models;

/**
 * Created by Jin on 2017-12-05.
 */

public class Post {

    private String key;
    private User user;
    private String postType;
    private String postTitle;
    private String postDesc;
    private String postImageUrl;
    private String postId;
    private long numComments;
    private long timeCreated;

    public Post(){}

    public Post(String key, User user, String postType, String postTitle, String postDesc, String postImageUrl, String postId, long numComments, long timeCreated) {
        this.key = key;
        this.user = user;
        this.postType = postType;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postImageUrl = postImageUrl;
        this.postId = postId;
        this.numComments = numComments;
        this.timeCreated = timeCreated;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public long getNumComments() {
        return numComments;
    }

    public void setNumComments(long numComments) {
        this.numComments = numComments;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }
}
