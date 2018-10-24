package com.zkq.snail.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;

/**
 * @author:zkq
 * create:2018/10/24 上午11:50
 * email:zkq815@126.com
 * desc: 一些暂时不用归类的工具
 */
public class Utils {


    public static int getActionBarHeight(@NonNull final Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{android.support.v7.appcompat.R.attr.actionBarSize});
        final int actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return actionBarHeight;
    }

    public static int getStatusBarHeight(@NonNull final Context context) {
        int result = 0;
        final int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
