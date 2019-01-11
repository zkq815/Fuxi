package com.zkq.fuxi.ui.main.universe;

import android.support.annotation.NonNull;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 * desc: 乾坤页面逻辑处理
 */
public class UniversePresenter implements UniverseContract.Presenter{
    private UniverseContract.View mView;
    public UniversePresenter(@NonNull UniverseContract.View view){
        this.mView = view;
        view.setPresenter(this);
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
