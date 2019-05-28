package com.zkq.fuxi.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;


import com.zkq.fuxi.base.application.MyApplication;
import com.zkq.fuxi.common.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author:zkq
 * create:2018/10/24 上午11:48
 * email:zkq815@126.com
 * desc:
 */
public class GUID {

    private static String sID;
    private static final String PATH_GUID = ".meizu_store_guid";

    static {
        sID = PreferenceUtil.getString(PreferenceKey.GUID, null);
    }

    private static class UUID {

        private static String uuid = null;

        private static String getUUID() {
            if (null == uuid) {
                uuid = java.util.UUID.randomUUID().toString();
            }

            return uuid;
        }

    }

    private static String getIdFromFile() {
        String id = null;

        try {
            final File file = new File(Environment.getExternalStorageDirectory(), PATH_GUID);

            if (file.exists() && !file.isFile()) {
                final boolean ret = file.delete();
                if (!ret) {
                    return null;
                }
            }

            if (file.exists()) {
                id = read(file);
            } else {
                write(file, UUID.getUUID());
                id = UUID.getUUID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public synchronized static String id() {
        if (null != sID) {
            return sID;
        }

        final Context context = MyApplication.getInstance();
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            sID = getIdFromFile();
        }

        if (TextUtils.isEmpty(sID)) {
            sID = UUID.getUUID();
        }

        PreferenceUtil.putString(PreferenceKey.GUID, sID);

        return sID;
    }

    private static String read(@NonNull final File file) throws IOException {
        try (final RandomAccessFile f = new RandomAccessFile(file, "r")) {
            final byte[] bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            return new String(bytes, Constants.ENCODING_UTF8);
        }
    }

    private static void write(@NonNull final File file, @NonNull final String guid) throws IOException {
        try (final FileOutputStream out = new FileOutputStream(file)) {
            out.write(guid.getBytes(Constants.ENCODING_UTF8));
        }
    }
}
