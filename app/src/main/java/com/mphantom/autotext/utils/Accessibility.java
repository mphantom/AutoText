package com.mphantom.autotext.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

import static android.content.Context.ACCESSIBILITY_SERVICE;

/**
 * Created by mphantom on 17/1/12.
 */

public class Accessibility {
    public static boolean checkAccessibilityenable(Context context) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(ACCESSIBILITY_SERVICE);
//        boolean enable = am.isEnabled();
//        boolean touchEnabled = am.isTouchExplorationEnabled();
        List<AccessibilityServiceInfo> list = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : list) {
            if (info.getId().equals("com.mphantom.autotext/.DetectService")) {
                return true;
            }
        }
        return false;
    }
}
