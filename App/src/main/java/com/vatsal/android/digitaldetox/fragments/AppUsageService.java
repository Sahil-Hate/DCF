package com.vatsal.android.digitaldetox.fragments;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class AppUsageService extends AccessibilityService {
    private int appLaunchCount = 0;
    private String targetPackageName = "com.example.app";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (event.getPackageName() != null && event.getPackageName().equals(targetPackageName)) {
                appLaunchCount++;
                Log.d("AppUsageService", "App launch count: " + appLaunchCount);
            }
        }
    }

    @Override
    public void onInterrupt() {
        // This method is called when the service is interrupted. Override to handle interruptions.
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.packageNames = new String[]{targetPackageName};
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }
}

