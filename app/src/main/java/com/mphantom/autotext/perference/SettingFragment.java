package com.mphantom.autotext.perference;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;

import com.mphantom.autotext.R;

/**
 * Created by wushaorong on 16-10-18.
 */

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }
}
