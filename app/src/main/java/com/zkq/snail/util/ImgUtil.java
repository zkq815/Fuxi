package com.zkq.snail.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import com.zkq.snail.BuildConfig;
import com.zkq.snail.R;
import com.zkq.snail.base.application.MyApplication;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author:zkq
 * create:2018/10/24 上午11:47
 * email:zkq815@126.com
 * desc: 图片加载工具
 */
public class ImgUtil {

    private static final String TAG = ImgUtil.class.getCanonicalName();

    @SuppressLint("StaticFieldLeak")
    private static Picasso sPicasso;

    public static Picasso getPicasso() {
        if (null == sPicasso) {
            synchronized (ImgUtil.class) {
                if (null == sPicasso) {
                    final Context context = MyApplication.getInstance();
                    sPicasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(context)).build();
//                    sPicasso.setIndicatorsEnabled(true);
                }
            }
        }

        return sPicasso;
    }

    /**
     * 退出程序时，shutdown Picasso的实例，优化cpu与内存占用
     */
    public static void destroy() {
        XLog.i(TAG, "destroy");

        final Picasso picasso;

        synchronized (ImgUtil.class) {
            picasso = sPicasso;
            sPicasso = null;
        }

        if (picasso != null) {
//            ThreadPool.exe(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        XLog.i(TAG, "shutdown picasso");
//                        picasso.shutdown();
//                    } catch (Exception e) {
//                        if (BuildConfig.LOG_DEBUG) {
//                            XLog.t(TAG, "destroy", e);
//                        }
//                    }
//                }
//            });

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    try {
                        XLog.i(TAG, "shutdown picasso");
                        picasso.shutdown();
                    } catch (Exception e) {
//                        if (BuildConfig.LOG_DEBUG) {
//                            XLog.t(TAG, "destroy", e);
//                        }
                    }
                }
            }, TimeUnit.SECONDS.toMillis(30));
        }
    }

    public static void load(String url, ImageView view) {
        if (filter(url)) {
            return;
        }
        getPicasso().load(deal(url)).fit().centerCrop().placeholder(R.color.grey_3e).into(view);
    }

    public static void loadWithNoHolder(String url, ImageView view) {
        if (filter(url)) {
            return;
        }
        getPicasso().load(deal(url)).into(view);
    }

    public static void loadWithHolder(String url, ImageView view) {
        if (filter(url)) {
            return;
        }
        getPicasso().load(deal(url)).placeholder(R.color.grey_3e).into(view);
    }

    public static void load(String url, ImageView view, Transformation transformation) {
        if (filter(url)) {
            return;
        }
        if (transformation != null) {
            getPicasso().load(deal(url)).placeholder(R.color.grey_3e).transform(transformation).into(view);
        } else {
            getPicasso().load(deal(url)).placeholder(R.color.grey_3e).into(view);
        }
    }

    public static void fitLoad(final String url, final ImageView imageView) {
        fitLoad(url, imageView, R.color.grey_3e);
    }

    public static void fitLoad(final String url, final ImageView imageView, int placeholderResId) {
        if (filter(url)) {
            return;
        }
        getPicasso().load(deal(url)).placeholder(placeholderResId).into(imageView);
    }

    public static void fitLoad(final String url, final Target target, int placeholderResId) {
        if (filter(url)) {
            return;
        }
        getPicasso().load(deal(url)).placeholder(placeholderResId).into(target);
    }

    public static void loadCrop(final String url, final ImageView imageView) {
        if (filter(url)) {
            return;
        }

        getPicasso().load(deal(url)).fit().centerCrop().transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
//                final long time = System.currentTimeMillis();
                final int w = source.getWidth();
                final int h = source.getHeight();
                final int crop = PxUtil.dp2px(6, MyApplication.getInstance());
                final int startX = w > crop * 2 ? crop : 0;
                final int startY = h > crop * 2 ? crop : 0;

                final int width = w > crop * 2 ? w - crop * 2 : w;
                final int height = h > crop * 2 ? h - crop * 2 : h;

                final Bitmap tmp = Bitmap.createBitmap(source, startX, startY, width, height);
                if (tmp != source) {
                    source.recycle();
                }
//                XLog.e("yc", "crop time: " + (System.currentTimeMillis() - time));
                return tmp;
            }

            @Override
            public String key() {
                return "crop";
            }
        }).into(imageView);
    }

    private static String deal(@NonNull final String url) {
        if (url.startsWith("//")) {
            return "http:" + url;
        }

        return url;
    }

    private static boolean filter(final String url) {
        if (url == null || url.trim().length() == 0) {
            return true;
        }

        return false;
    }
}
