//package com.zkq.snail.util;
//
//import android.content.Context;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.StringRes;
//import android.view.Gravity;
//import android.widget.Toast;
//
//import com.zkq.snail.R;
//import com.zkq.snail.base.application.MApplication;
//
///**
// * 魅族toast，统一规范 <a href="http://design.flyme.cn/design.html#/version=6&t_id=31&a_id=123&version_id=11">Toast & SnackBars</a>
// *
// * @author yc
// * @since 16/9/8
// */
//public class MeiZuToast {
//
//    public static void show(final String info) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, info).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset).longDuration();
//
//        toast.show();
//    }
//
//    public static void show(@StringRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset).longDuration();
//
//        toast.show();
//    }
//
//    public static void showShort(final String info) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, info).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset);
//
//        toast.show();
//    }
//
//    public static void showShort(@StringRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset);
//
//        toast.show();
//    }
//
//    public static void show(final String info, @DrawableRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, info, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset).longDuration();
//
//        toast.show();
//    }
//
//    public static void show(@StringRes final int strId, @DrawableRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, strId, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset).longDuration();
//
//        toast.show();
//    }
//
//    public static void showShort(final String info, @DrawableRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, info, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset);
//
//        toast.show();
//    }
//
//    public static void showShort(@StringRes final int strId, @DrawableRes final int resId) {
//        final Context context = MApplication.getInstance();
//        final int offset = context.getResources().getDimensionPixelSize(R.dimen.toast_bottom_distance);
//        final Toast toast = Toast.makeText(context, strId, resId).gravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, offset);
//
//        toast.show();
//    }
//}
