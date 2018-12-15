package com.zkq.fuxi.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author:zkq
 * create:2018/10/24 上午11:48
 * email:zkq815@126.com
 * desc:
 */
public class CountDownN implements Runnable {

    private final Handler handler;
    private ScheduledExecutorService executorService = null;
    private ICountDown countDownListener = null;
    private boolean isDestroyed = false;

    public CountDownN() {
        handler = new Handler(Looper.getMainLooper());
    }

    private void ensureExecutor() {
        if (null == executorService || executorService.isShutdown()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
    }

    public void start() {
        if (isDestroyed) {
            XLog.e(this, "already been destroyed");
            return;
        }
        ensureExecutor();
        executorService.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        if (null != executorService) {
            executorService.shutdown();
            executorService = null;
        }
    }

    public void destroy() {
        isDestroyed = true;
        stop();

        countDownListener = null;
    }

    @Override
    public void run() {
        if (isDestroyed) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isDestroyed) {
                    return;
                }
                if (null != countDownListener) {
                    countDownListener.onCountDown();
                }
            }
        });
    }

    public interface ICountDown {
        void onCountDown();
    }

    public void setCountDownListener(final ICountDown listener) {
        this.countDownListener = listener;
    }
}
