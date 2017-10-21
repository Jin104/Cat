package com.jin.cat.Knowledge;

public class ItemObject {

    private int knowledgeImage;
    private String knowledgeName;
    private String knowledgeDetail;

    public ItemObject(int screenShot, String musicName, String musicAuthor) {
        this.knowledgeImage = screenShot;
        this.knowledgeName = musicName;
        this.knowledgeDetail = musicAuthor;
    }

    public int getKnowledgeImage() {
        return knowledgeImage;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public String getKnowledgeDetail() {
        return knowledgeDetail;
    }
}
