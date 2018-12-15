package com.zkq.fuxi.ui.main.home;

import android.support.annotation.NonNull;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 */
public class DefaultPresenter implements DefaultContract.Presenter{
    private DefaultContract.View mView;
    public DefaultPresenter(@NonNull DefaultContract.View view){
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
