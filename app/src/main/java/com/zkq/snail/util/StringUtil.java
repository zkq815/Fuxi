package com.zkq.snail.util;

import android.text.TextUtils;


import com.zkq.snail.base.application.MyApplication;
import com.zkq.snail.common.Constants;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:zkq
 * create:2018/10/24 下午2:38
 * email:zkq815@126.com
 * desc: 
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String getResourceString(int strId) {
        return MyApplication.getInstance().getResources().getString(strId);
    }

    public static String trim(final String src, final char c) {
        if (null == src) {
            return null;
        }

        int start = 0, last = src.length() - 1;
        int end = last;
        while ((start <= end) && (src.charAt(start) == c)) {
            start++;
        }
        while ((end >= start) && (src.charAt(end) == c)) {
            end--;
        }

        if (start == 0 && end == last) {
            return src;
        }

        return src.substring(start, end);
    }

    /**
     * 将价格最后面的.0和.00去掉
     */
    public static String deleteDecimalPoint(String goodsPrice) {

        String price = String.valueOf(goodsPrice);
        String tempPrice;
        if (price.contains(".")) {
            if (price.split("\\.")[1].equals("0") || price.split("\\.")[1].equals("00")) {
                tempPrice = price.split("\\.")[0];
            } else if (price.split("\\.")[1].equals("0起") || price.split("\\.")[1].equals("00起")) {
                tempPrice = price.split("\\.")[0] + "起";
            } else {
                tempPrice = price;
            }
        } else {
            tempPrice = price;
        }
        return tempPrice;
    }

    public static boolean equals(final String str1, final String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    private static SimpleDateFormat sdf = null;

    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable ignored) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }

    // 返回单位是米
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {

        double EARTH_RADIUS = 6371.393;
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;

        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 米和千米单位转换
     */
    public static String meter2KM(int meter) {
        String tempDistance;

        if (meter < 1000) {//小于一千米，显示米
            tempDistance = String.valueOf(meter + "米");
        } else {//大于一千米显示千米
            DecimalFormat df = new DecimalFormat("#.00");
            tempDistance = String.valueOf(df.format((double) meter / 1000.00) + "千米");

        }

        return tempDistance;
    }

    /**
     * 判断是否包含中文
     *
     * @param str 目标字符串
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 时间戳转换成字符窜
     * */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /**
     * 倒计时时间处理
     */
    public static String[] getCountdownString(final long time) {
        long left = time / 1000;

        long second = left - left / 60 * 60;
        left /= 60;

        long minute = left - left / 60 * 60;
        left /= 60;

        long hour = left % 24;
        left /= 24;

        long day = left;

        String timeDay = String.valueOf(day);
        String timeHour = String.valueOf(hour);
        String timeMinute = String.valueOf(minute);
        String timeSecond = String.valueOf(second);

        if (timeDay.length() != 2) {
            timeDay = "0" + String.valueOf(day);
        }

        if (timeHour.length() != 2) {
            timeHour = "0" + String.valueOf(hour);
        }

        if (timeMinute.length() != 2) {
            timeMinute = "0" + String.valueOf(minute);
        }

        if (timeSecond.length() != 2) {
            timeSecond = "0" + String.valueOf(second);
        }

        String[] arr;
        if (timeDay.equals("00")) {
            arr = new String[3];
            arr[0] = timeHour;
            arr[1] = timeMinute;
            arr[2] = timeSecond;
        } else {
            arr = new String[4];
            arr[0] = timeHour;
            arr[1] = timeMinute;
            arr[2] = timeSecond;
            arr[3] = timeDay;
        }
        return arr;
    }

    public static String addHttpToStart(String url){
        if(isNotEmpty(url) && url.startsWith(Constants.HTTP)){
            return url;
        }else{
            return Constants.HTTP + url;
        }
    }

}
