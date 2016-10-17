package com.mphantom.autotext;

import android.content.Context;

/**
 * Created by wushaorong on 16-10-17.
 */

public interface SelfAvailablePreference {
    /**
     * @return the availability of the preference. Please make sure the availability in managed
     * profile is taken into account.
     */
    boolean isAvailable(Context context);
}