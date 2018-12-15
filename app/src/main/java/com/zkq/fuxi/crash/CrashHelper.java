package com.zkq.fuxi.crash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zkq.fuxi.BuildConfig;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class CrashHelper {

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    public static void init(@NonNull final Context context) {
//        final boolean main = BuildConfig.CATCH_CRASH_MAIN;
        final boolean main = false;
//        final boolean background = BuildConfig.CATCH_CRASH_BACKGROUND;
        final boolean background = true;

        if (!main && !background) {
            return;
        }

        CrashCatcher.install(new CrashCatcher.CrashHandler() {
            @Override
            public boolean handle(@NonNull Thread thread, @NonNull Throwable throwable) {
//                final Activity activity = ActivityStack.top();
//                if (activity != null) {
//                    CrashDialog.showStackTrace(activity, throwable);
//                    return true;
//                }
//                return false;

                throwable.printStackTrace();

//                final Intent intent = new Intent(context, MzService.class);
//                intent.putExtra(MzService.TYPE, MzService.TYPE_CRASH_REPORT);
//                final Bundle bundle = new Bundle();
//                bundle.putSerializable(MzService.DATA, new TrackCrash(throwable));
//                intent.putExtras(bundle);
//                context.startService(intent);
//                ActivityStack.exitApp();
                return true;
            }
        }, main, background);
    }
}
