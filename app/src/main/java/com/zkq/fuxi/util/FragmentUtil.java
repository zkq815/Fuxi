package com.zkq.fuxi.util;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc: fragment工具类
 */

public class FragmentUtil {

    public static void startFragment(AppCompatActivity activity, int containerId, @NonNull Fragment fragment, Bundle params) {
        //如果有mainFragment
        if (params != null) {
            fragment.setArguments(params);
        }

        if (CheckActivity.isActivityAlive(activity)) {
            activity.getSupportFragmentManager().beginTransaction().add(containerId,
                    fragment, null).
                    commitAllowingStateLoss();
        }
    }

    public static void startFragment(AppCompatActivity activity, int containerId,
                                     @NonNull Fragment fragment, Bundle params, String tag) {
        //如果有mainFragment
        if (params != null) {
            fragment.setArguments(params);
        }

        if (CheckActivity.isActivityAlive(activity)) {
            activity.getSupportFragmentManager().beginTransaction().add(containerId,
                    fragment, tag).
                    commitAllowingStateLoss();
        }
    }

    public static void addFragment(Activity activity, int containerId, Fragment fromF, Fragment f, boolean isBack, int anim) {
        if (activity == null) {
            return;
        }

        if (activity instanceof AppCompatActivity) {
            FragmentManager fm = ((AppCompatActivity) activity).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (fromF != null) {
                ft.hide(fromF);
            }
            ft.add(containerId, f);
            if (anim != FragmentTransaction.TRANSIT_UNSET) {
                ft.setTransition(anim);
            }
            if (isBack) {
                ft.addToBackStack(null);
            }
            try {
                ft.commitAllowingStateLoss();
            } catch (Exception ignore) {
                // 在 activity 已经 destory 时，可能抛出IllegalStateException异常。
                // 由于在这种状态下，已经无法处理界面的切换，也不需要做其他恢复操作，所以这里只捕获异常，避免程序崩溃。
            }
        }
    }
}
