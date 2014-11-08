package com.twochooseone.android.hearthstonehub.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

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
