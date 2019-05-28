package com.zkq.fuxi.crash;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import com.zkq.fuxi.util.XLog;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class CrashCatcher {

    private static final String TAG = "CrashCatcher";

    public interface ICrashHandler {

        /**
         * monkey test时，是否捕捉崩溃
         *
         * @return true：照常捕捉；false：不消费，继续抛出或交由Thread.getDefaultUncaughtExceptionHandler处理
         */
        boolean consumeMonkey();

        /**
         * 处理捕捉的crash
         *
         * @param thread    崩溃发生的线程
         * @param throwable 抛出的throwable
         * @return 是否消费掉此次崩溃，不消费继续抛出或交由Thread.getDefaultUncaughtExceptionHandler处理
         */
        boolean handle(@NonNull final Thread thread, @NonNull final Throwable throwable);
    }

    public static abstract class CrashHandler implements ICrashHandler {

        @Override
        public boolean consumeMonkey() {
            return false;
        }

    }


    /**
     * 用于退出Loop.loop()循环
     */
    private static class Quit extends RuntimeException {
    }

    private static Thread.UncaughtExceptionHandler sUncaughtExceptionHandler;
    private static boolean sInstalled;

    private static boolean sMain;
    private static boolean sBackground;

    public static synchronized void install(final ICrashHandler crashHandler, final boolean main, final boolean background) {
        if (!main && !background) {
            XLog.i(TAG, "at lease one of main & background should be true");
            return;
        }

        if (sInstalled) {
            XLog.i(TAG, "already installed, uninstall before install again");
            return;
        }

        sMain = main;
        sBackground = background;

        if (main) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

//                    while (true) {
//                        try {
//                            Looper.loop();
//                        } catch (Throwable throwable) {
//                            if (throwable instanceof Quit) {
//                                return;
//                            }
//
//                            if (crashHandler == null) {
//                                throw throwable;
//                            }
//
//                            if (!crashHandler.consumeMonkey() && ActivityManager.isUserAMonkey()) {
//                                throw throwable;
//                            }
//
//                            if (!crashHandler.handle(Looper.getMainLooper().getThread(), throwable)) {
//                                throw throwable;
//                            }
//                        }
//                    }

                }
            });
        }

        if (background) {
            sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    if (crashHandler == null || !crashHandler.handle(thread, ex)) {
                        if (sUncaughtExceptionHandler != null) {
                            sUncaughtExceptionHandler.uncaughtException(thread, ex);
                        }
                    }
                }
            });
        }

        sInstalled = true;
    }

    public static synchronized void uninstall() {
        if (!sInstalled) {
            return;
        }

        if (sBackground) {
            Thread.setDefaultUncaughtExceptionHandler(sUncaughtExceptionHandler);
        }

        if (sMain) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    throw new Quit();
                }
            });
        }

        sMain = false;
        sBackground = false;

        sUncaughtExceptionHandler = null;
        sInstalled = false;
    }
}
