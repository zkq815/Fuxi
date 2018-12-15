package com.zkq.fuxi.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zkq.fuxi.common.ActivityStack;
import com.zkq.fuxi.ui.main.MainActivity;

import static com.zkq.fuxi.base.util.Preconditions.checkNotNull;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class IntentUtils {

    public static boolean startActivity(@NonNull final Context context, @NonNull final Intent intent) {
        try {
            checkNotNull(context, "context is null");
            checkNotNull(intent, "intent is null");

            // android23及以下，非Activity需要添加Intent.FLAG_ACTIVITY_NEW_TASK标志
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N
                    && !Activity.class.isAssignableFrom(context.getClass())) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            context.startActivity(intent);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static final Class<? extends AppCompatActivity> HOME_CLASS = MainActivity.class;

    public static void launchHome(@NonNull final Context context, final Intent intent) {
        final Intent i = intent != null ? intent : new Intent();
        i.setClass(context, HOME_CLASS);

        final boolean hasHome = ActivityStack.hasActivity(HOME_CLASS);
        if (!hasHome) {
            startActivity(context, i);
        }
    }

    public static void launchHome(@NonNull final Context context) {
        launchHome(context, new Intent());
    }

    private static String launchClassName;

    public static boolean isLaunch(@NonNull Activity activity) {
        try {
            checkNotNull(activity, "activity is null");

            if (launchClassName == null) {
                final Intent intent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
                if (intent != null) {
                    final ComponentName componentName = intent.getComponent();
                    if (componentName != null) {
                        launchClassName = componentName.getClassName();
                    }
                }
            }

            if (launchClassName != null && activity.getClass().getName().equals(launchClassName)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean openBrowser(@NonNull final Context context, final String url) {
        return startActivity(context, new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}
