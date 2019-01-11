package com.zkq.fuxi.ui.main.prime;

import android.support.annotation.NonNull;

/**
 * @author:zkq
 * create:2018/10/24 上午11:43
 * email:zkq815@126.com
 * desc: 洪荒页面逻辑处理
 */
public class PrimePresenter implements PrimeContract.Presenter{
    private PrimeContract.View mView;
    public PrimePresenter(@NonNull PrimeContract.View view){
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
