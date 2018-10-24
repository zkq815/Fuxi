package com.zkq.snail.util;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.zkq.snail.BuildConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * @author:zkq
 * create:2018/10/24 上午11:46
 * email:zkq815@126.com
 * desc: 倒计时
 */
public abstract class CountDown implements Runnable {

    private final long countDownTime;
    private final long currentTime;
    private Handler handler;
    private ScheduledExecutorService executorService = null;

    protected CountDown(final long countDownTime) {
        this.countDownTime = countDownTime;
        this.currentTime = SystemClock.elapsedRealtime();
        handler = new Handler(Looper.getMainLooper());
    }

    public boolean isOver() {
        return getRemainTime() <= 0;
    }

    protected long getRemainTime() {
        final long time = SystemClock.elapsedRealtime();
        return countDownTime - (time - currentTime);
    }

    public void start() {
        if (BuildConfig.LOG_DEBUG) {
            XLog.d("start");
        }
        if (null == executorService || executorService.isShutdown()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }

        executorService.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public final void run() {
        final long remainTime = getRemainTime();
        if (remainTime >= 0) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onCountDown(remainTime);
                }
            });
        } else {
            executorService.shutdown();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    onFinish();
                }
            });
        }
    }

    public void destroy() {
        if (BuildConfig.LOG_DEBUG) {
            XLog.d("destroy");
        }
        if (null != executorService) {
            executorService.shutdown();
            executorService = null;
        }
        handler = null;
    }

    public abstract void onCountDown(final long time);

    public abstract void onFinish();
}
