package com.zkq.snail.base.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zkq.snail.base.Info;
import com.zkq.snail.base.handler.IHandler;
import com.zkq.snail.base.handler.LeakFreeHandler;
import com.zkq.snail.common.ActivityStack;
import com.zkq.snail.common.SimpleOkHttpStack;
import com.zkq.snail.crash.CrashHelper;
import com.zkq.weapon.application.BaseApplication;

import okhttp3.OkHttpClient;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class MyApplication extends BaseApplication implements IHandler {

    @SuppressLint("StaticFieldLeak")
    private static MyApplication instance;
    protected RequestQueue mRequestQueue;
    protected RequestQueue mLogRequestQueue;

    private static final int KILL_MSG = 211;
    private static final int KILL_MSG_WAIT_TIME = 5 * 55 * 1000;

    @Override
    public void handleMessage(Message msg) {
//        ImgUtil.destroy();
    }

    @NonNull
    private final Application application;

    private final LeakFreeHandler handler;

    public MyApplication() {
        super();
        this.application = this;

        handler = new LeakFreeHandler(this);
    }

    public MyApplication(@NonNull final Application application) {
        super();
        this.application = application;

        handler = new LeakFreeHandler(this);
    }

    @NonNull
    public Application getApplication() {
        return application;
    }

    public static Application getApp() {
        if (instance != null) {
            return instance.application;
        }

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        if (LeakWatcher.init(application)) {
//            // LeakCanary会创建单独的进程用于内存堆分析，这里不应该调用我们的初始化代码，直接返回即可
//            return;
//        }

        instance = this;

        Info.init(this);

        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (ActivityStack.size() == 0) {
                    // 第一个创建的activity
//                    Tracker.init();
                }
                ActivityStack.push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
//                if (BuildConfig.LOG_DEBUG) {
//                    if (ActivityStack.atTop(activity)) {
//                        ActivityStack.printStack();
//                    }
//                }
                handler.removeMessages(KILL_MSG);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                handler.sendEmptyMessageDelayed(KILL_MSG, KILL_MSG_WAIT_TIME);
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStack.pop(activity);
            }
        });

        CrashHelper.init(this);

    }

    public static Application getInstance() {
        return instance.getApplication();
    }

    protected OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    public static RequestQueue getRequestQueue() {
        if (instance.mRequestQueue == null) {
            instance.mRequestQueue = Volley.newRequestQueue(instance.getApplication(), new SimpleOkHttpStack(instance.getOkHttpClient()));
        }
        return instance.mRequestQueue;
    }

    public static RequestQueue getLogRequestQueue() {
        if (instance.mLogRequestQueue == null) {
            instance.mLogRequestQueue = Volley.newRequestQueue(instance.getApplication(), new SimpleOkHttpStack(instance.getOkHttpClient()));
        }
        return instance.mLogRequestQueue;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
