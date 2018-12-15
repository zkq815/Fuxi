package com.zkq.fuxi.util;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author:zkq
 * create:2018/10/24 下午2:38
 * email:zkq815@126.com
 * desc: 
 */
public class TypeUtil {

    private static final DecimalFormat sDecimalFormat = new DecimalFormat("##0.00");
    /**
     * 省略小数点尾部的0
     */
    private static final DecimalFormat sDecimalFormat1 = new DecimalFormat("##0.##");

    public static String toFloat2(float f) {
        return sDecimalFormat.format(f);

//        BigDecimal b = new BigDecimal(f);
//        float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//        return f1;
    }

    public static String formatPriceCent(final BigDecimal b) {
        if (b == null) {
            return "0.00";
        }

        final float price = b.setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue() * 1.0f / 100;
        return sDecimalFormat.format(price);
    }

    public static String formatPrice(@Nullable final BigDecimal b) {
        if (b == null) {
            return "0.00";
        }

        final float price = b.floatValue();
        return sDecimalFormat.format(price);
    }


    public static String formatPrice1(final double d) {
        return sDecimalFormat1.format(d);
    }

    public static String formatPrice1(@Nullable final BigDecimal b) {
        if (b == null) {
            return "0";
        }

        final float price = b.floatValue();
        return sDecimalFormat1.format(price);
    }

    public static String formatPriceCent1(final long priceCent) {
        return sDecimalFormat1.format(((float) priceCent / 100));
    }
}
