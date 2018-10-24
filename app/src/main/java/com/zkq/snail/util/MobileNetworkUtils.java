package com.zkq.snail.util;

import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

/**
 * @author:zkq
 * create:2018/10/24 上午11:48
 * email:zkq815@126.com
 * desc: 获取网络状态工具类
 */
public final class MobileNetworkUtils {

    /**
     * Network type is wifi
     */
    private static final int NETWORK_TYPE_WIFI = -1;

    /**
     * Network type is unknown
     */
    private static final int NETWORK_TYPE_UNKNOWN = 0;

    public static boolean isNetAvailable(Context context) {
        Object connManager = context == null ? null : context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager == null ? null
                : ((ConnectivityManager) connManager).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getState() == State.CONNECTED;
    }

    public static int getNetworkType(Context context) {
        if (isWIFI(context)) {
            return NETWORK_TYPE_WIFI;
        }
        PackageManager pm = context.getPackageManager();
        if ((pm.checkPermission(permission.READ_PHONE_STATE,
                context.getPackageName())) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager mTelephonyMgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return mTelephonyMgr.getNetworkType();
        }
        return NETWORK_TYPE_UNKNOWN;
    }

    private static boolean isWIFI(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == State.CONNECTED) {
                    return info.getType() == ConnectivityManager.TYPE_WIFI;
                }
            }
        }
        return false;
    }

}
