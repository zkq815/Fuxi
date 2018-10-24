package com.zkq.snail.util;


import com.zkq.snail.BuildConfig;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author:zkq
 * create:2018/10/24 上午11:47
 * email:zkq815@126.com
 * desc: MD5工具
 */
public class Md5Util {

    public static String getMd5(final String path) {
        return getMd5(new File(path));
    }

    public static String getMd5(final File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        final byte[] buf = new byte[8192];

        int len;

        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final FileInputStream is = new FileInputStream(file);
            try {
                while (-1 != (len = is.read(buf))) {
                    digest.update(buf, 0, len);
                }
                final BigInteger bigInt = new BigInteger(1, digest.digest());
                String output = bigInt.toString(16);
                for (; output.length() < 32; ) {
                    output = "0" + output;
                }

                return output;
            } finally {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }

        } catch (Exception e) {
            if (BuildConfig.LOG_DEBUG) {
                XLog.t("getMd5", e);
            }
        }

        return null;
    }
}
