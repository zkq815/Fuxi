package com.zkq.snail.ui.main.explore;

import android.support.annotation.NonNull;

import com.zkq.snail.ui.main.memory.MemoryContract;

/**
 * author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 */
public class ExplorePresenter implements ExploreContract.Presenter{
    private ExploreContract.View mView;
    public ExplorePresenter(@NonNull ExploreContract.View view){
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
