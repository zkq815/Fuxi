package com.zkq.fuxi.util;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.View;

import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:46
 * email:zkq815@126.com
 * desc: Fragment工具
 */
public class FragmentUtils {

    public static Fragment getFragment(@NonNull final FragmentManager fm, @Nullable final Bundle savedInstanceState, @NonNull final Class<? extends Fragment> clz) {
        Fragment f = null;
        if (savedInstanceState != null) {
            final List<Fragment> fragmentList = fm.getFragments();
            if (null != fragmentList) {
                for (Fragment fragment : fragmentList) {
                    if (fragment.getClass() == clz) {
                        f = fragment;
                    }
                }
            }
        }

        return f;
    }

    public static Fragment getOrCreateFragment(@NonNull final FragmentManager fm, @Nullable final Bundle savedInstanceState, @NonNull final Class<? extends Fragment> clz) {
        Fragment f = getFragment(fm, savedInstanceState, clz);

        if (f == null) {
            try {
                f = clz.newInstance();
            } catch (Exception e) {
                XLog.t("getOrCreateFragment: " + savedInstanceState + " " + clz, e);
            }
        }

        return f;
    }

    /**
     * 只适用于设置了{@link FragmentPagerAdapter}的ViewPager
     *
     * @param fm      {@link FragmentManager}
     * @param contain fragment容器
     * @param index   fragment在容器中的序号
     * @return fm中获取已存在的fragment
     */
    public static Fragment getFragmentByTag(@NonNull final FragmentManager fm, @NonNull final View contain, final int index) {
        return fm.findFragmentByTag(makeFragmentName(contain.getId(), index));
    }

    /**
     * 根据contain的ID和fragment的序号获取fragment的tag<br/>
     *
     * @param viewId contain的ID
     * @param id     fragment在adapter中的序号
     * @return fragment的TAG
     * @see FragmentPagerAdapter#makeFragmentName(int, long)
     */
    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}
