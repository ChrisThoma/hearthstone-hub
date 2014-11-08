package com.twochooseone.android.hearthstonehub.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.twochooseone.android.hearthstonehub.MainApp;

/**
 * Created by christhoma on 11/7/14.
 */
public class HearthstoneBaseActivity extends ActionBarActivity {


    /*
     * Have to set the actionbar as a toolbar! In all things
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp) getApplication()).inject(this);

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = this.getWindow();

        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
