package com.jin.cat.Knowledge.Massage;

import android.graphics.drawable.Drawable;

/**
 * Created by sunmoon on 2017-11-05.
 */

public class MassageItem2 {

    private Drawable iconDrawable;
    private String titleStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }
    public void setTitle(String title) {
        titleStr = title;
    }

    public Drawable getIcon() {
        return this.iconDrawable;
    }
    public String getTitle() {
        return this.titleStr;
    }
}
