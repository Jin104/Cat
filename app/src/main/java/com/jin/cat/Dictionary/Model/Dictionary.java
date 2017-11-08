package com.jin.cat.Dictionary.Model;

/**
 * Created by rakha on 2017-10-22.
 */

public class Dictionary {

    private int dictionaryImage;
    private String dictionaryTitle;

    public Dictionary(int dictionaryImage, String dictionaryTitle) {
        this.dictionaryImage = dictionaryImage;
        this.dictionaryTitle = dictionaryTitle;
    }

    public int getDictionaryImage() {
        return dictionaryImage;
    }

    public String getDictionaryTitle() {
        return dictionaryTitle;
    }

}