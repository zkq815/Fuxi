package com.zkq.fuxi.ui.main.explore;

import android.support.annotation.NonNull;

import com.zkq.fuxi.ui.main.memory.MemoryContract;

/**
 * @author:zkq
 * create:2018/10/24 上午11:43
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
