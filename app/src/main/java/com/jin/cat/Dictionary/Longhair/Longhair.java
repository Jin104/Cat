package com.jin.cat.Dictionary.Longhair;

/**
 * Created by rakha on 2017-11-05.
 */

public class Longhair {

    private int longhairImage;
    private String longhairTitle;

    public Longhair(String longhairTitle, int longhairImage) {
        this.longhairImage = longhairImage;
        this.longhairTitle = longhairTitle;
    }

    public int getLonghairImage() {
        return longhairImage;
    }

    public String getLonghairTitle() {
        return longhairTitle;
    }
}
