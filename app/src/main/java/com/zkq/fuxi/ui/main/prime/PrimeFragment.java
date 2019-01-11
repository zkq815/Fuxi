package com.zkq.fuxi.ui.main.prime;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.fuxi.databinding.FragmentPrimeBinding;
import com.zkq.weapon.base.BaseFragment;

/**
 * @author:zkq
 * create:2018/10/24 上午11:44
 * email:zkq815@126.com
 * desc: 洪荒页面
 */

public class PrimeFragment extends BaseFragment implements PrimeContract.View{

    private FragmentPrimeBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_prime,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull PrimeContract.Presenter presenter) {

    }
}
