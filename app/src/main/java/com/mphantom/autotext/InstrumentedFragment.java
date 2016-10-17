package com.mphantom.autotext;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.util.Log;

/**
 * Created by wushaorong on 16-10-17.
 */

public abstract class InstrumentedFragment extends PreferenceFragment {

    public static final int UNDECLARED = 100000;

    public static final int PREFERENCE_ACTIVITY_FRAGMENT = UNDECLARED + 1;

    protected abstract int getMetricsCategory();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }


    @Override
    public void onResume() {
        super.onResume();
//        MetricsLogger.visible(getActivity(), getMetricsCategory());
        Log.d(getActivity().getLocalClassName(), "metricsCategory==" + getMetricsCategory());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getActivity().getLocalClassName(), "metricsCategory==" + getMetricsCategory());
//        MetricsLogger.hidden(getActivity(), getMetricsCategory());
    }
}
