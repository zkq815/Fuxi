package com.zkq.snail.customview.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc:
 */
public class BasePagerAdapter<T> extends FragmentPagerAdapter {

    private List<T> mDataSet;
    @NonNull
    private final SparseArray<WeakReference<Fragment>> fragments = new SparseArray<>();

    private FragmentProducer mFragmentProducer;

    public BasePagerAdapter(FragmentManager fm, FragmentProducer fragmentProducer) {
        super(fm);
        mDataSet = new ArrayList<>();
        mFragmentProducer = fragmentProducer;
    }

    /**
     * 设置数据源
     */
    public void setDataSet(List<T> dataSet) {
        if (dataSet != null && dataSet.size() > 0) {
            mDataSet = dataSet;
            notifyDataSetChanged();
        }
    }


    public List<T> getDataSet() {
        return mDataSet;
    }

    @Override
    public int getCount() {
        return null == mDataSet ? 0 : mDataSet.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.get(position) != null) {
            return fragments.get(position).get();
        } else {
            Fragment fragment = mFragmentProducer.produceFragment(position);
            fragments.put(position, new WeakReference<Fragment>(fragment));
            return fragment;
        }
    }

    @NonNull
    public SparseArray<WeakReference<Fragment>> getFragments() {
        return fragments;
    }

    public interface FragmentProducer {
        Fragment produceFragment(int position);
    }

}
