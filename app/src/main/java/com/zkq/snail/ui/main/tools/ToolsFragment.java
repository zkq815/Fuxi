package com.zkq.snail.ui.main.tools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.snail.R;
import com.zkq.snail.databinding.FragmentToolsBinding;
import com.zkq.weapon.base.BaseFragment;

/**
 * @author:zkq
 * create:2018/10/24 上午11:43
 * email:zkq815@126.com
 * desc: 工具页面
 */

public class ToolsFragment extends BaseFragment implements ToolsContract.View{

    private FragmentToolsBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tools,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull ToolsContract.Presenter presenter) {

    }
}
