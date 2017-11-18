package com.jin.cat.interfaces;

import android.view.View;

/**
 * Created by sunmoon on 2017-11-17.
 */

public interface ItemClickListener {
    //Toast
    void onClick(View view, int position, boolean isLongClick);
}
