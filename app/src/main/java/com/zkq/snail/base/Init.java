package com.zkq.snail.base;


import com.zkq.snail.util.PreferenceKey;
import com.zkq.snail.util.PreferenceUtil;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class Init {

    public static class Permission {

        /**
         * 用户是否授权
         */
        private static boolean permissionGranted;

        static {
            permissionGranted = PreferenceUtil.getBoolean(PreferenceKey.NETWORK_PERMISSION, false);
        }

        public static void grantPermission() {
            PreferenceUtil.putBoolean(PreferenceKey.NETWORK_PERMISSION, true);
            permissionGranted = true;
        }

        public static boolean isPermissionGranted() {
            return permissionGranted;
        }
    }

}