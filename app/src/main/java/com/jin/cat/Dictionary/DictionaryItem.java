package com.jin.cat.Dictionary;

/**
 * Created by rakha on 2017-10-22.
 */

public class DictionaryItem {

    private int dictionaryImage;
    private String dictionaryName;

    public DictionaryItem(int dictionaryImage, String dictionaryName) {
        this.dictionaryImage = dictionaryImage;
        this.dictionaryName = dictionaryName;
    }

    public int getDictionaryImage() {
        return dictionaryImage;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

}