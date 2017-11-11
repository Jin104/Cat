package com.jin.cat.Dictionary.Model;

/**
 * Created by rakha on 2017-10-22.
 */

public class Dictionary {

    private int dictionaryImage;
    private String dictionaryTitle;
    private String dictionaryCount;

    public Dictionary(int dictionaryImage, String dictionaryTitle, String dictionaryCount) {
        this.dictionaryImage = dictionaryImage;
        this.dictionaryTitle = dictionaryTitle;
        this.dictionaryCount = dictionaryCount;
    }

    public int getDictionaryImage() {
        return dictionaryImage;
    }

    public String getDictionaryTitle() {
        return dictionaryTitle;
    }

    public String getDictionaryCount() {
        return dictionaryCount;
    }
}