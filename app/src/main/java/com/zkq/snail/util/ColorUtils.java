package com.zkq.snail.util;

import android.graphics.Color;
import android.text.TextUtils;

/**
 * @author:zkq
 * create:2018/10/24 上午11:47
 * email:zkq815@126.com
 * desc:
 */
public class ColorUtils {
    public final static int COLOR_ILLEGAL = 0;
    public static int parseColor(String colorString) {
        if (TextUtils.isEmpty(colorString)) {
            return COLOR_ILLEGAL;
        }
        try {
            return Color.parseColor(colorString);
        }catch (Exception e){
            return COLOR_ILLEGAL;
        }
    }

    public static boolean isLegal(int bgColor) {
        return bgColor != COLOR_ILLEGAL;
    }
}
