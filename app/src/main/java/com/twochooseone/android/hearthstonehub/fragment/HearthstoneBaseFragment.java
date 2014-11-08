package com.twochooseone.android.hearthstonehub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twochooseone.android.hearthstonehub.MainApp;

import butterknife.ButterKnife;

/**
 * Created by christhoma on 11/8/14.
 */
public class HearthstoneBaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp) getActivity().getApplication()).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
