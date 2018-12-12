package com.zkq.snail.ui.main.memory;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.snail.R;
import com.zkq.snail.databinding.FragmentMemoryBinding;
import com.zkq.weapon.base.BaseFragment;

/**
 * @author:zkq
 * create:2018/10/24 上午11:42
 * email:zkq815@126.com
 * desc: 记忆页面
 */

public class MemoryFragment extends BaseFragment implements MemoryContract.View {
    private FragmentMemoryBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_memory,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull MemoryContract.Presenter presenter) {

    }
}
