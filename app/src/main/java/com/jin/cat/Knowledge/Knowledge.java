package com.jin.cat.Knowledge;

public class Knowledge {

    private int knowledgeImage;
    private String knowledgeTitle;
    private String knowledgeDesc;

    public Knowledge(String knowledgeTitle, String knowledgeDesc, int knowledgeImage) {
        this.knowledgeTitle = knowledgeTitle;
        this.knowledgeDesc = knowledgeDesc;
        this.knowledgeImage = knowledgeImage;
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public String getKnowledgeDesc() {
        return knowledgeDesc;
    }

    public int getKnowledgeImage() {
        return knowledgeImage;
    }
}
