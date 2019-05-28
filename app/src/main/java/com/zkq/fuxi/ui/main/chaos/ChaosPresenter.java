package com.zkq.fuxi.ui.main.chaos;

import androidx.annotation.NonNull;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 * desc: 混沌页面逻辑处理
 */
public class ChaosPresenter implements ChaosContract.Presenter{
    private ChaosContract.View mView;
    public ChaosPresenter(@NonNull ChaosContract.View view){
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
