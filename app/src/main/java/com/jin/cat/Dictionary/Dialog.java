package com.jin.cat.Dictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jin.cat.R;

/**
 * Created by sunmoon on 2017-10-22.
 */

public class Dialog extends Activity implements View.OnClickListener {

    // ImageButton long1_if_forward = (ImageButton) findViewById(R.id.dialog_long1_plus);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_long1);
        findViewById(R.id.dialog_long1_plus).setOnClickListener(this);


    }

    public void onClick(View v) {
        switch(v.getId()) {
            /*case R.id.dialog_long1_close_btn:
                this.finish();
                break;*/

            case R.id.dialog_long1_plus:
                startActivity(new Intent(this, Long1_plus.class));
                break;
        }
    }
}
