package com.mphantom.autotext;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by wushaorong on 16-8-22.
 */

public class DetectService extends AccessibilityService {
    public static final String TAG = DetectService.class.getName();

    private static DetectService mInstance = null;

    public DetectService() {
    }

    public static DetectService getInstance() {
        if (mInstance == null) {
            synchronized (DetectService.class) {
                if (mInstance == null) {
                    mInstance = new DetectService();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
//            event.getText().toString();
            Log.d(TAG, "textSize==" + event.getText().size());
            String input = event.getText().toString();
            String content = input.substring(1, input.length() - 1);
            Log.d(TAG, "text==" + content);
            Log.d(TAG, "beforetext==" + event.getBeforeText());
//            Bundle arguments = new Bundle();
//            arguments.putCharSequence(AccessibilityNodeInfo
//                    .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "newtexttopopulateedittext");
//            event.getSource().performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);

//            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData clip = ClipData.newPlainText("label", "MyData");
//            clipboard.setPrimaryClip(clip);
//            event.getSource().performAction(AccessibilityNodeInfo.ACTION_PASTE);
//            getWindowChange(content);
        }

    }

//    public void getWindowChange(String input) {
//        AccessibilityNodeInfo nodeInfo = this.getRootInActiveWindow();
//        AccessibilityNodeInfo targetNode = null;
//        targetNode = findNode(nodeInfo, input);
//        if (targetNode != null){
//            Log.d(TAG,targetNode.getClassName().toString());
////            performGlobalAction();
//            targetNode.setText("testforme");
//        }
////        if (targetNode.isClickable()) {
////            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
////        }
//    }
//
//    private AccessibilityNodeInfo findNode(AccessibilityNodeInfo node, String text) {
//        List<AccessibilityNodeInfo> list = node.findAccessibilityNodeInfosByText(text);
//        if (list == null || list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }

    @Override
    public void onInterrupt() {
    }


    /**
     * 此方法用来判断当前应用的辅助功能服务是否开启
     *
     * @param context
     * @return
     */
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.d("wenming", e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }
}
