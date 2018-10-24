package com.zkq.snail.crash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class CrashTest {

    private static final String KEY_CRASH_TEST = "crash_test";
    private static final String KEY_CRASH_TYPE_MAIN = "crash_type_main";

    public static void handleIntent(@NonNull final Activity activity, final Intent intent) {
        if (intent != null) {
            final boolean crashTest = intent.getBooleanExtra(KEY_CRASH_TEST, false);
            if (crashTest) {
                final String pkg = activity.getCallingPackage();
                if ("com.yc.testsuite".equals(pkg)) {
                    activity.setResult(Activity.RESULT_OK);

                    final boolean main = intent.getBooleanExtra(KEY_CRASH_TYPE_MAIN, false);
                    if (main) {
                        throwMain();
                    } else {
                        throwBackground();
                    }
                }
            }
        }
    }

    private static class CrashTestException extends RuntimeException {

        CrashTestException(String detailMessage) {
            super(detailMessage);
        }

    }

    private static void throwMain() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                throw new CrashTestException("主线程崩溃收集测试");
            }
        });
    }

    private static void throwBackground() {
        final HandlerThread handlerThread = new HandlerThread("后台线程");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                handlerThread.quitSafely();
                throw new CrashTestException("后台线程崩溃收集测试");
            }
        });
    }
}
