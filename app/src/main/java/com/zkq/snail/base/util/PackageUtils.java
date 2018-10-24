package com.zkq.snail.base.util;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class PackageUtils {

    public static boolean isInstalled(@NonNull final Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
