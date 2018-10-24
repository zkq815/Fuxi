package com.zkq.snail.util;

import android.os.Environment;


import com.zkq.snail.BuildConfig;
import com.zkq.snail.common.BuildInfo;

import java.io.File;

/**
 * @author:zkq
 * create:2018/10/24 上午11:46
 * email:zkq815@126.com
 * desc: 文件工具
 */
public class FileUtils {

    public final static String MEIZU = "meizu";
    public final static String DOWNLOAD = "download";
    public final static String PATCH = "patch";
    public final static String TINKER = "tinker";

    public static boolean checkDir(final File dir) {
        boolean ret = true;

        if (dir.exists() && !dir.isDirectory()) {
            if (!dir.delete()) {
                if (BuildConfig.LOG_DEBUG) {
                    XLog.e("delete failed: " + dir.getAbsolutePath());
                }
                ret = false;
            }
        }

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                if (BuildConfig.LOG_DEBUG) {
                    XLog.e("mkdirs failed: " + dir.getAbsolutePath());
                }
                ret = false;
            }
        }

        return ret;
    }

    public static File getDownloadDir() {
        final File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), BuildInfo.APPLICATION_ID);

        checkDir(dir);

        return dir;
    }

    public static File getPatchDir() {
        final File dir = new File(getDownloadDir(), PATCH);

        checkDir(dir);

        return dir;
    }

    public static File getTinkerPatchDir() {
        final File dir = new File(getPatchDir(), TINKER);

        checkDir(dir);

        return dir;
    }
}
