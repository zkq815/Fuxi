package com.zkq.snail.ui.main.explore;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.snail.R;
import com.zkq.snail.base.ui.BaseFragment;
import com.zkq.snail.databinding.FragmentExploreBinding;

/**
 * @author:zkq
 * create:2018/10/24 上午11:44
 * email:zkq815@126.com
 * desc: 探索页面
 */

public class ExploreFragment extends BaseFragment implements ExploreContract.View{

    private FragmentExploreBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull ExploreContract.Presenter presenter) {

    }
}
