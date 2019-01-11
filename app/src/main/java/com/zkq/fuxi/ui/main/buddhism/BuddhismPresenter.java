package com.zkq.fuxi.ui.main.buddhism;

import android.support.annotation.NonNull;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 * desc: 佛教页面逻辑处理
 */
public class BuddhismPresenter implements BuddhismContract.Presenter{
    private BuddhismContract.View mView;
    public BuddhismPresenter(@NonNull BuddhismContract.View view){
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
