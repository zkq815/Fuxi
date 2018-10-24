package com.zkq.snail.base;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import com.zkq.snail.base.application.MyApplication;
import com.zkq.snail.base.util.PackageUtils;


/**
 * @author zkq
 * @since 2018/10/24
 */
public class Info {

    /**
     * 是否魅族手机，根据是否有魅族账号系统判断
     */
    private static boolean isFlyme;

    public static void init(@NonNull final Context context) {
        isFlyme = PackageUtils.isInstalled(context, "com.meizu.account");
    }

    public static boolean isFlyme() {
        return isFlyme;
    }

    public static int getScreenWidth() {
        return getScreenWidth(MyApplication.getInstance());
    }

    public static int getScreenWidth(@NonNull final Context context) {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        return getScreenHeight(MyApplication.getInstance());
    }

    public static int getScreenHeight(@NonNull final Context context) {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getScreenDensity() {
        return getScreenDensity(MyApplication.getInstance());
    }

    public static float getScreenDensity(@NonNull final Context context) {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

    /**
     * 序列号不推荐使用
     */
    @Deprecated
    public static String getSerialNumber() {
        return Build.SERIAL;
    }
}
