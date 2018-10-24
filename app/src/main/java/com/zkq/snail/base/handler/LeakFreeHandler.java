package com.zkq.snail.base.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class LeakFreeHandler extends Handler {

    private final WeakReference<IHandler> ref;

    public LeakFreeHandler(IHandler f) {
        this.ref = new WeakReference<>(f);
    }

    @Override
    public void handleMessage(Message msg) {
        if (null != ref.get()) {
            ref.get().handleMessage(msg);
        }
    }
}
