package com.jin.cat.models;

public class Knowledge {

    private int knowledgeImage;
    private String knowledgeTitle;

    public Knowledge(String knowledgeTitle, int knowledgeImage) {
        this.knowledgeTitle = knowledgeTitle;
        this.knowledgeImage = knowledgeImage;
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public int getKnowledgeImage() {
        return knowledgeImage;
    }
}
