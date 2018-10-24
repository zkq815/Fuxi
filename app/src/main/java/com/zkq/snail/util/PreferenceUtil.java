package com.zkq.snail.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zkq.snail.base.application.MyApplication;

/**
 * @author:zkq
 * create:2018/10/24 上午11:48
 * email:zkq815@126.com
 * desc: SharedPreferences读写工具
 */
public class PreferenceUtil {

    private static SharedPreferences getDefault() {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
    }

    public static String getString(final PreferenceKey key, final String def) {
        return getDefault().getString(key.getKey(), def);
    }

    @Deprecated
    public static String getStringPref(Context ctx, String key, String defaultValue) {
        return getDefault().getString(key, defaultValue);
    }

    public static String getStringPref(PreferenceKey key, String defaultValue) {
        return getDefault().getString(key.getKey(), defaultValue);
    }

    @Deprecated
    public static void saveStringPref(PreferenceKey key, String value) {
        putString(key, value);
    }

    public static void putString(final PreferenceKey key, final String value) {
        final SharedPreferences.Editor edit = getDefault().edit();
        edit.putString(key.getKey(), value);
        edit.apply();
    }

    @Deprecated
    public static void saveStringPref(Context ctx, String key, String value) {
        final SharedPreferences.Editor edit = getDefault().edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static long getLong(final PreferenceKey key, final long def) {
        return getDefault().getLong(key.getKey(), def);
    }

    public static void putLong(final PreferenceKey key, final long value) {
        final SharedPreferences.Editor edit = getDefault().edit();
        edit.putLong(key.getKey(), value);
        edit.apply();
    }

    public static boolean getBoolean(final PreferenceKey key, final boolean def) {
        return getDefault().getBoolean(key.getKey(), def);
    }

    public static void putBoolean(final PreferenceKey key, final boolean value) {
        final SharedPreferences.Editor edit = getDefault().edit();
        edit.putBoolean(key.getKey(), value);
        edit.apply();
    }
}

