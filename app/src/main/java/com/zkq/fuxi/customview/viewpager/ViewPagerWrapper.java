package com.zkq.fuxi.customview.viewpager;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc:
 */
public class ViewPagerWrapper<T extends Object> {
    private ScrollableViewPager mViewPager;

    private ViewPager.OnPageChangeListener mPageChangeListener;

    private final static int OFF_SCREEN_PAGE_LIMIT = 1;

    public ViewPagerWrapper(ScrollableViewPager viewPager) {
        this(viewPager, OFF_SCREEN_PAGE_LIMIT);
    }

    public ViewPagerWrapper(ScrollableViewPager viewPager, int limit) {
        mViewPager = viewPager;
        mViewPager.setOffscreenPageLimit(limit);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (null != mPageChangeListener) {
                    mPageChangeListener.onPageScrolled(i, v, i1);
                }
            }

            @Override
            public void onPageSelected(int i) {
                if (null != mPageChangeListener) {
                    mPageChangeListener.onPageSelected(i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (null != mPageChangeListener) {
                    mPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        });
    }

    public void setPageChangeListener(ViewPager.OnPageChangeListener pageChangeListener) {
        this.mPageChangeListener = pageChangeListener;
    }

    public void setAdapter(BasePagerAdapter<T> adapter) {
        mViewPager.setAdapter(adapter);
    }

    public void setPageSelection(int position) {
        setPageSelection(position, true);
    }

    public void setPageSelection(int position, boolean smoothScroll) {
        mViewPager.setCurrentItem(position, smoothScroll);
    }

    public void setPageScrollEnabled(boolean isPagingEnabled) {
        mViewPager.setPageScrollEnabled(isPagingEnabled);
    }

    public BasePagerAdapter<T> getAdapter() {
        return (BasePagerAdapter<T>) mViewPager.getAdapter();
    }

    public void setDataSet(List<T> dataSet) {
        getAdapter().setDataSet(dataSet);
    }

    public List<T> getDataSet() {
        return getAdapter().getDataSet();
    }

    public SparseArray<WeakReference<Fragment>> getFragments() {
        return getAdapter().getFragments();
    }

    public Fragment getFragment(int position) {
        WeakReference<Fragment> weakReference = getFragments().get(position);
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getCurrentItemPosition() {
        if (mViewPager != null) {
            return mViewPager.getCurrentItem();
        }
        return 0;
    }
}
