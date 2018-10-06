package com.zkq.snail.ui.main.memory;

import android.support.annotation.NonNull;

import com.zkq.snail.ui.main.mine.MineContract;

/**
 * author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 */
public class MemoryPresenter implements MemoryContract.Presenter{
    private MemoryContract.View mView;
    public MemoryPresenter(@NonNull MemoryContract.View view){
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
