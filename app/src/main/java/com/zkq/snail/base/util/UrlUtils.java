package com.zkq.snail.base.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zkq.snail.base.uri.UriUtils;


/**
 * @author zkq
 * @since 2018/10/24
 */
public class UrlUtils {

    public static boolean openUrl(@NonNull final Context context, @NonNull final String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        String targetUrl;

        final Uri uri = Uri.parse(url);
        final String scheme = uri.getScheme();
        final String host = uri.getHost();

        if ("http".equals(scheme)
                || "https".equals(scheme)) {

            if ("cart.mall.meizu.com".equals(host)) {

                // 购物车
                targetUrl = "mzstore://mall/cart?internal=true";

            } else if (url.contains("detail.mall.meizu.com/item/")
                    || url.contains("detail.meizu.com/item/")) {

                // 详情页
                targetUrl = "mzstore://mall/detail?internal=true&url=" + url;

            } else {
                // 普通url链接
                targetUrl = "mzstore://mall/web?internal=true&url=" + url;
            }
        } else {
            targetUrl = url;
        }

        return UriUtils.openUri(context, Uri.parse(targetUrl));
    }

}
