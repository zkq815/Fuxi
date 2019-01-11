package com.zkq.fuxi.ui.main.universe;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.fuxi.R;
import com.zkq.fuxi.databinding.FragmentUniverseBinding;
import com.zkq.weapon.base.BaseFragment;
import com.zkq.weapon.base.WebViewPluginActivity;
import com.zkq.weapon.constants.WeaponConstants;

/**
 * @author:zkq
 * create:2018/10/24 上午11:43
 * email:zkq815@126.com
 * desc: 乾坤页面
 */

public class UniverseFragment extends BaseFragment implements UniverseContract.View{

    private FragmentUniverseBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_universe,container,false);
        mBinding.tvMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewPluginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(WeaponConstants.WEB_TITLE, "test");
                bundle.putString(WeaponConstants.WEB_URL, "http://www.baidu.com");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void setPresenter(@NonNull UniverseContract.Presenter presenter) {

    }
}
