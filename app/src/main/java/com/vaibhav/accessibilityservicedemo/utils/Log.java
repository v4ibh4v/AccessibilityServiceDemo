package com.vaibhav.accessibilityservicedemo.utils;

import static com.vaibhav.accessibilityservicedemo.utils.Constants.LOG_ENABLED;

public class Log {
    private static String app = "AccessibilityServiceDemo";

    public static final void d(Throwable throwable) {
        if (LOG_ENABLED)
            android.util.Log.d(app, "", throwable);
    }

    public static final void d(Object object) {
        if (LOG_ENABLED)
            android.util.Log.d(app, object != null ? "*** " + object.toString() : null);
    }

    public static final void d(Object object, Throwable throwable) {
        if (LOG_ENABLED)
            android.util.Log.d(app, object != null ? "*** " + object.toString() : null, throwable);
    }
}
