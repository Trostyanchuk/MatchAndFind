package com.matchandfind.utils;

import android.content.Context;

public final class PreferencesUtil {

    private static final String PREF_NAME = "MathAndFindFrefs";
    private static final String FIRST_LOGIN_ATTR = "firstLogin";

    public static void markLoginAsFirstIfNotMarked(Context context) {
//        if (!alreadyLoggedIn(context)) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(FIRST_LOGIN_ATTR, true).apply();
//        }
    }

    public static boolean alreadyLoggedIn(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean(FIRST_LOGIN_ATTR, false);
    }
}
