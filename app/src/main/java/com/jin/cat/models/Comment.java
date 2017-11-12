package com.jin.cat.models;

import java.util.Date;

/**
 * Created by rakha on 2017-11-12.
 */

public class Comment {

    private User user;
    private String commentId;
    private String comment;
    private long timeCreated;

    public Comment(){

    }

    public Comment(User user, String commentId, String comment, long timeCreated) {
        this.user = user;
        this.commentId = commentId;
        this.comment = comment;
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }
}
