package com.twochooseone.android.hearthstonehub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twochooseone.android.hearthstonehub.R;

import butterknife.ButterKnife;

/**
 * Created by christhoma on 11/8/14.
 */
public class NaxxramasFragment extends HearthstoneBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_naxxramas, container, false);
        ButterKnife.inject(this, v);
        return v;
    }
}
