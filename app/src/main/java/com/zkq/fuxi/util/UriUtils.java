package com.zkq.fuxi.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import static com.zkq.fuxi.base.IntentUtils.startActivity;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class UriUtils {

    public static final String INTERNAL = "internal";

    public static boolean openUrl(@NonNull final Context context, final String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        return openUri(context, Uri.parse(url));
    }

    public static boolean openUri(@NonNull final Context context, @NonNull final Uri uri) {
        final String scheme = uri.getScheme();

        final Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if ("tel".equals(scheme)
                || "market".equals(scheme)) {
            return startActivity(context, intent);
        }

        if ("mzstore".equals(scheme)) {
            // 备用链接
            final String alternative = uri.getQueryParameter("alternative");
            final Uri alternativeUri = TextUtils.isEmpty(alternative) ? null : Uri.parse(alternative);

            final boolean ret = startActivity(context, intent);

            if (ret) {
                return true;
            }

            if (alternativeUri != null) {
                return startActivity(context, new Intent(Intent.ACTION_VIEW, alternativeUri));
            }
        }

        return false;
    }

}
