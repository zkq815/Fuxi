package com.zkq.fuxi.ui.main.prime;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.weapon.base.BaseFragment;

/**
 * @author:zkq
 * create:2018/10/24 上午11:44
 * email:zkq815@126.com
 * desc: 洪荒页面
 */

public class PrimeFragment extends BaseFragment implements PrimeContract.View{

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_prime,container,false);
        ButterKnife.bind(rootView);
        return rootView;
    }

    @Override
    public void setPresenter(@NonNull PrimeContract.Presenter presenter) {

    }
}
