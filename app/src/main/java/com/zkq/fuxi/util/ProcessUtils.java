package com.zkq.fuxi.util;

import android.app.ActivityManager;
import android.content.Context;


import com.zkq.fuxi.base.application.MyApplication;

import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:50
 * email:zkq815@126.com
 * desc:
 */
public class ProcessUtils {

    public static void killAllProcess() {
        killAllOtherProcess(MyApplication.getInstance());

        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private static List<ActivityManager.RunningAppProcessInfo> getProcessInfoList(final Context context) {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }

        return am.getRunningAppProcesses();
    }

    private static void killAllOtherProcess(final Context context) {
        final List<ActivityManager.RunningAppProcessInfo> appProcessList = getProcessInfoList(context);

        if (appProcessList == null) {
            return;
        }

        for (ActivityManager.RunningAppProcessInfo ai : appProcessList) {
            if (ai.uid == android.os.Process.myUid() && ai.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(ai.pid);
            }
        }

    }

    private static ActivityManager.RunningAppProcessInfo getProcessInfo(final Context context) {
        final List<ActivityManager.RunningAppProcessInfo> appProcessList = getProcessInfoList(context);

        if (null != appProcessList) {
            for (ActivityManager.RunningAppProcessInfo ai : appProcessList) {
                if (ai.pid == android.os.Process.myPid()) {
                    return ai;
                }
            }
        }

        return null;
    }

    public static String getProcessName(final Context context) {
        final ActivityManager.RunningAppProcessInfo info = getProcessInfo(context);
        if (null == info) {
            return null;
        }

        return info.processName;
    }

    public static boolean isMainProcess(final Context context) {
        final ActivityManager.RunningAppProcessInfo info = getProcessInfo(context);
        return null != info && info.processName.equals(context.getPackageName());
    }

//    public static boolean isUpdateProcess(final Context context) {
//        final ActivityManager.RunningAppProcessInfo info = getProcessInfo(context);
//        return null != info && info.processName.endsWith(":update");
//    }

}
