package com.zkq.fuxi.util;

import android.util.Log;

import com.zkq.fuxi.BuildConfig;

/**
 * @author:zkq
 * create:2018/10/24 上午11:50
 * email:zkq815@126.com
 * desc:
 */
public class XLog {

    private static final boolean isLog = BuildConfig.LOG_DEBUG;

    public static void d(Object tag, Object msg) {
        if (isLog){
            Log.d(tag.toString(), msg.toString());
        }
    }

    public static void d(Object msg) {
        if (isLog) {
            Log.d(getClassName(), msg.toString());
        }
    }

    public static void v(Object tag, Object msg) {
        if (isLog) {
            Log.v(tag.toString(), msg.toString());
        }
    }

    public static void v(Object msg) {
        if (isLog) {
            Log.v(getClassName(), msg.toString());
        }
    }

    public static void i(Object tag, Object msg) {
        if (isLog) {
            Log.i(tag.toString(), msg.toString());
        }
    }

    public static void i(Object msg) {
        if (isLog) {
            Log.i(getClassName(), msg.toString());
        }
    }

    public static void w(Object tag, Object msg) {
        if (isLog) {
            Log.w(tag.toString(), msg.toString());
        }
    }

    public static void w(Object msg) {
        if (isLog) {
            Log.w(getClassName(), msg.toString());
        }
    }

    public static void e(Object tag, Object msg) {
        if (isLog) {
            Log.e(tag.toString(), msg.toString());
        }
    }

    public static void e(Object msg) {
        if (isLog) {
            Log.e(getClassName(), msg.toString());
        }
    }

    public static void t(Object msg, Throwable t) {
        if (isLog) {
            Log.w(getClassName(), msg.toString(), t);
        }
    }

    public static void t(Object tag, Object msg, Throwable throwable) {
        if (isLog) {
            Log.w(tag.toString(), msg.toString(), throwable);
        }
    }

    private static String getClassName() {
        String result;
        // 这里的数组的index2是根据你工具类的层级做不同的定义，这里仅仅是关键代码
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());
        return result;
    }

}
