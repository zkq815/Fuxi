package com.zkq.fuxi.ui.main;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

/**
 * @author:zkq
 * create:2018/10/24 上午11:46
 * email:zkq815@126.com
 * desc:
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    HomePresenter(@NonNull HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mView.setUpNavigation();

//        initDelay();
    }

    private void initDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 800);
    }

    @Override
    public void unsubscribe() {
    }
}
