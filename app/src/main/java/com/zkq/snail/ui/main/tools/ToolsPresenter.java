package com.zkq.snail.ui.main.tools;

import android.support.annotation.NonNull;

/**
 * @author:zkq
 * time:2018/10/6:16:11
 * email:zkq815@126.com
 */
public class ToolsPresenter implements ToolsContract.Presenter{
    private ToolsContract.View mView;
    public ToolsPresenter(@NonNull ToolsContract.View view){
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
