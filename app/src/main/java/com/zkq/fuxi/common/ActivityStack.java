package com.zkq.fuxi.common;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.zkq.fuxi.BuildConfig;
import com.zkq.fuxi.util.XLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zkq
 * @since 2018/10/24
 * Activity栈
 */
@MainThread
public class ActivityStack {

    private static class ActivityRecord {
        final WeakReference<Activity> activityRef;
        boolean stopped = false;

        private ActivityRecord(@NonNull final Activity activity) {
            this.activityRef = new WeakReference<>(activity);
        }
    }

    private static final List<ActivityRecord> sActivityStack = new ArrayList<>();
    private static final Map<String, ActivityRecord> sActivityRecordMap = new HashMap<>();

    /**
     * Activity入栈
     */
    public static void push(@NonNull final Activity activity) {
        final ActivityRecord record = new ActivityRecord(activity);
        sActivityStack.add(record);
        sActivityRecordMap.put(activity.toString(), record);
    }

    /**
     * 出栈顶部Activity
     */
    @Nullable
    public static Activity pop() {
        if (sActivityStack.size() > 0) {
            final ActivityRecord rec = sActivityStack.remove(sActivityStack.size() - 1);

            if (null != rec) {
                if (null != rec.activityRef.get()) {
                    sActivityRecordMap.remove(rec.activityRef.toString());
                } else {
                    if (sActivityRecordMap.containsValue(rec)) {
                        for (String key : sActivityRecordMap.keySet()) {
                            if (null != key && sActivityRecordMap.get(key) == rec) {
                                sActivityRecordMap.remove(key);
                                break;
                            }
                        }
                    }
                }
                return rec.activityRef.get();
            }
        }

        return null;
    }

    /**
     * 出栈指定Activity
     */
    @Nullable
    public static Activity pop(@NonNull final Activity activity) {
        for (int i = sActivityStack.size() - 1; i >= 0; i--) {
            final ActivityRecord rec = sActivityStack.get(i);
            if (null != rec && activity == rec.activityRef.get()) {
                sActivityRecordMap.remove(activity.toString());
                return sActivityStack.remove(i).activityRef.get();
            }
        }

        return null;
    }

    /**
     * 返回顶部Activity
     */
    @Nullable
    public static Activity top() {
        while (sActivityStack.size() > 0) {
            final ActivityRecord rec = sActivityStack.get(sActivityStack.size() - 1);
            if (null == rec || null == rec.activityRef.get()) {
                sActivityStack.remove(rec);
                if (null != rec) {
                    if (sActivityRecordMap.containsValue(rec)) {
                        for (String key : sActivityRecordMap.keySet()) {
                            if (null != key && sActivityRecordMap.get(key) == rec) {
                                sActivityRecordMap.remove(key);
                                break;
                            }
                        }
                    }
                }
                continue;
            }

            return rec.activityRef.get();
        }
        return null;
    }

    /**
     * 判断activity是否在栈顶
     */
    public static boolean atTop(@NonNull final Activity activity) {
        return activity == top();
    }

    public static void clearAll() {
        sActivityStack.clear();
        sActivityRecordMap.clear();
    }

    /**
     * 关闭所有activity
     */
    public static void exitApp() {
        for (int i = 0; i < sActivityStack.size(); i++) {
            final ActivityRecord rec = sActivityStack.get(i);
            if (null == rec || null == rec.activityRef.get()) {
                continue;
            }

            final Activity activity = rec.activityRef.get();

            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

        clearAll();
    }

    public static void printStack() {
//        if (BuildConfig.LOG_DEBUG) {
//            final StringBuilder msg = new StringBuilder("Print activity stack ->\n");
//            for (ActivityRecord rec : sActivityStack) {
//                if (null != rec && null != rec.activityRef.get()) {
//                    msg.append("\t->\t").append(rec.activityRef.get()).append("\n");
//                }
//            }

//            XLog.e(msg.toString());
//        }
    }

    public static boolean hasActivity(@NonNull final Class<? extends Activity> clz) {
        for (ActivityRecord rec : sActivityStack) {
            if (null != rec && null != rec.activityRef.get() && rec.activityRef.get().getClass() == clz) {
                return true;
            }
        }

        return false;
    }

    public static Activity getActivity(@NonNull final Class<? extends Activity> clz) {
        for (ActivityRecord rec : sActivityStack) {
            if (null != rec && null != rec.activityRef.get()) {
                final Activity activity = rec.activityRef.get();
                if (activity.getClass() == clz && !activity.isDestroyed() && !activity.isFinishing()) {
                    return activity;
                }
            }
        }

        return null;
    }

    public static void onActivityStarted(@NonNull final Activity activity) {
        final ActivityRecord record = sActivityRecordMap.get(activity.toString());
        if (null != record && null != record.activityRef.get()) {
            record.stopped = false;
        }
    }

    public static void onActivityStopped(@NonNull final Activity activity) {
        final ActivityRecord record = sActivityRecordMap.get(activity.toString());
        if (null != record && null != record.activityRef.get()) {
            record.stopped = true;
        }
    }

    public static boolean isBackground() {
        for (ActivityRecord record : sActivityStack) {
            if (null != record && null != record.activityRef.get()) {
                if (!record.stopped) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 获取当前栈中activity数量
     */
    public static int size() {
        int size = 0;

        for (ActivityRecord record : sActivityStack) {
            if (record != null && record.activityRef.get() != null) {
                size++;
            }
        }

        return size;
    }

    /**
     * 获取当前栈中存活的activity数量
     */
    public static int aliveSize() {
        int size = 0;

        for (ActivityRecord record : sActivityStack) {
            if (record != null && record.activityRef.get() != null) {
                final Activity activity = record.activityRef.get();
                if (!activity.isDestroyed() && !activity.isFinishing()) {
                    size++;
                }
            }
        }

        return size;
    }
}
