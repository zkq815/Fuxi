package com.zkq.snail.util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import static com.zkq.snail.base.util.Preconditions.checkNotNull;

/**
 * @author:zkq
 * create:2018/10/24 上午11:47
 * email:zkq815@126.com
 * desc:
 */
public class IntentUtils {

    public static final String SHOW_REFRESH = "show_refresh";

    public static final String SOURCE_PAGE = "source_page";

    public static final String TRACK_ID = "track_id";

    public static boolean startActivity(@NonNull final Context context, @NonNull final Intent intent, final String pageCode) {
        try {
            final Context c = checkNotNull(context, "context cannot be null");
            final Intent i = checkNotNull(intent, "intent cannot be null");

            if (!TextUtils.isEmpty(pageCode)) {
                i.putExtra(SOURCE_PAGE, pageCode);
            }

            c.startActivity(i);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
