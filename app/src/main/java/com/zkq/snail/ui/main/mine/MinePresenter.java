package com.zkq.snail.ui.main.mine;

import android.support.annotation.NonNull;

import com.zkq.snail.ui.main.home.DefaultContract;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 */
public class MinePresenter implements MineContract.Presenter{
    private MineContract.View mView;
    public MinePresenter(@NonNull MineContract.View view){
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
