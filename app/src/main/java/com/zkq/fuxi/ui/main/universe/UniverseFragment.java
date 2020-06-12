package com.zkq.fuxi.ui.main.universe;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkq.fuxi.R;
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

    private View rootView;

    @BindView(R.id.tv_mine)
    TextView tvMine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_universe,container,false);
        ButterKnife.bind(rootView);
        tvMine.setOnClickListener(new View.OnClickListener() {
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
        return rootView;
    }

    @Override
    public void setPresenter(@NonNull UniverseContract.Presenter presenter) {

    }
}
