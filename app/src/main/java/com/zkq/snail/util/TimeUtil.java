package com.zkq.snail.util;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * @author:zkq
 * create:2018/10/24 上午11:49
 * email:zkq815@126.com
 * desc: 
 */
public class TimeUtil {
    // 两次点击按钮之间的点击间隔不能少于100毫秒
    private static final int MIN_CLICK_DELAY_TIME = 100;
    private static long lastClickTime;

    public static GregorianCalendar tomorrowStartTime(@NonNull final GregorianCalendar today) {
        final GregorianCalendar tomorrowStart = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        tomorrowStart.setTimeInMillis(tomorrowStart.getTimeInMillis() + TimeUnit.DAYS.toMillis(1));
        return tomorrowStart;
    }

    private static boolean sameYear(@NonNull final GregorianCalendar calendar1, @NonNull final GregorianCalendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    private static boolean sameDay(@NonNull final GregorianCalendar calendar1, @NonNull final GregorianCalendar calendar2) {
        return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean sameDate(@NonNull final GregorianCalendar calendar1, @NonNull final GregorianCalendar calendar2) {
        return sameYear(calendar1, calendar2) && sameDay(calendar1, calendar2);
    }

    private static boolean theYearBefore(@NonNull final GregorianCalendar src, @NonNull final GregorianCalendar dest) {
        return src.get(Calendar.YEAR) < dest.get(Calendar.YEAR);
    }

    public static boolean theDayBefore(@NonNull final GregorianCalendar src, @NonNull final GregorianCalendar dest) {
        return theYearBefore(src, dest) || sameYear(src, dest) && src.get(Calendar.DAY_OF_YEAR) < dest.get(Calendar.DAY_OF_YEAR);
    }

    private static boolean theYearAfter(@NonNull final GregorianCalendar src, @NonNull final GregorianCalendar dest) {
        return src.get(Calendar.YEAR) > dest.get(Calendar.YEAR);
    }

    public static boolean theDayAfter(@NonNull final GregorianCalendar src, @NonNull final GregorianCalendar dest) {
        return theYearAfter(src, dest) || sameYear(src, dest) && src.get(Calendar.DAY_OF_YEAR) > dest.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 防止快速点击，设置保护
     */
    public static boolean isEffectiveClick() {
        boolean flag = true;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
