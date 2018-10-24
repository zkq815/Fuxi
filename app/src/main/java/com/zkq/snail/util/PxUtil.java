package com.zkq.snail.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author:zkq
 * create:2018/10/24 下午1:31
 * email:zkq815@126.com
 * desc: 
 */
public class PxUtil {
    public static int dp2px(final float dp, Context context) {

        XLog.e("zkq","当前"+context.getResources().getDisplayMetrics());
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static float sp2px(int size, Context context) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, size, context.getResources().getDisplayMetrics());
    }

    /**
     * dp转px
     * @param dp
     */
    public static int dp2px(final int dp,Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    /**
     * px转换dip
     * */
    public static int px2dip(final int px,Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
