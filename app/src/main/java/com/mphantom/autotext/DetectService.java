package com.mphantom.autotext;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.mphantom.autotext.database.Expandhelper;

import java.util.List;

/**
 * Created by wushaorong on 16-8-22.
 */

public class DetectService extends AccessibilityService {
    public static final String TAG = DetectService.class.getName();
    public List<ExpandModle> expandModles;
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
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "oncreate");
        expandModles = Expandhelper.getInstance().getExpands();
        Log.d(TAG, "expandModeles==" + expandModles.size());
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "accessibility receive");
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.d(TAG, "**方法五** 当前窗口焦点对应的包名为： =" + event.getPackageName().toString());
            Log.d(TAG, "**方法五** 当前窗口焦点对应的包名为： =" + event.getClassName().toString());
        }
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
//            Log.d(TAG, "textSize==" + event.getText().size());
            String input = event.getText().toString();
            String content = input.substring(1, input.length() - 1);
            Log.d(TAG, "text==" + content);
            Log.d(TAG, "beforetext==" + event.getBeforeText());
            if (content.equals(getString(R.string.clipboard))) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", clipboard.getPrimaryClip()
                        .getItemAt(clipboard.getPrimaryClip().getItemCount() - 1).getText());
                clipboard.setPrimaryClip(clip);
                event.getSource().performAction(AccessibilityNodeInfo.ACTION_PASTE);
            } else {
                for (ExpandModle modle : expandModles) {
                    if (modle.getKey().equals(content)) {
                        Log.d(TAG, "expandModle Key==" + modle.getKey() + "value" + modle.getValue());
                        Bundle arguments = new Bundle();
                        arguments.putCharSequence(AccessibilityNodeInfo
                                .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, modle.getValue());
                        event.getSource().performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//                        event.getSource().setText(modle.getValue());
                        break;
                    }
                }
            }

//                getWindowChange(content);
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

//
//    /**
//     * 此方法用来判断当前应用的辅助功能服务是否开启
//     *
//     * @param context
//     * @return
//     */
//    public static boolean isAccessibilitySettingsOn(Context context) {
//        int accessibilityEnabled = 0;
//        try {
//            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
//                    Settings.Secure.ACCESSIBILITY_ENABLED);
//        } catch (Settings.SettingNotFoundException e) {
//            Log.d("wenming", e.getMessage());
//        }
//
//        if (accessibilityEnabled == 1) {
//            String services = Settings.Secure.getString(context.getContentResolver(),
//                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
//            if (services != null) {
//                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
//            }
//        }
//        return false;
//    }
}
