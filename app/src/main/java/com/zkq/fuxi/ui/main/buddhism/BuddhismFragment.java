package com.zkq.fuxi.ui.main.buddhism;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.fuxi.databinding.FragmentBuddhismBinding;
import com.zkq.weapon.base.BaseFragment;

/**
 * @author:zkq
 * create:2018/10/24 上午11:42
 * email:zkq815@126.com
 * desc: 佛教页面
 */

public class BuddhismFragment extends BaseFragment implements BuddhismContract.View {
    private FragmentBuddhismBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_buddhism,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull BuddhismContract.Presenter presenter) {

    }
}
