package com.zkq.fuxi.customview.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc:
 */
public class ScrollableViewPager extends ViewPager {
    private boolean isPagingEnabled = true;

    public ScrollableViewPager(Context context) {
        super(context);
    }

    public ScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPageScrollEnabled(boolean isPagingEnabled) {
        this.isPagingEnabled = isPagingEnabled;
    }
}
