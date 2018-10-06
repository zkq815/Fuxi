package com.zkq.snail.ui.main.toolutil;

import android.app.Activity;

/**
 * Created by zkq
 * on 2018/1/23.
 */

public class CheckActivity {

    public static boolean isActivityAlive(Activity activity) {
        return activity != null && !activity.isFinishing() && !activity.isDestroyed();
    }
}
