package com.zkq.snail.ui.main.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.snail.R;
import com.zkq.snail.base.ui.BaseFragment;
import com.zkq.snail.databinding.FragmentDefaultBinding;

/**
 * Created by zkq
 * on 2018/8/26.
 * 首页
 */

public class DefaultFragment extends BaseFragment implements DefaultContract.View{
    private FragmentDefaultBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_default,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull DefaultContract.Presenter presenter) {

    }
}
