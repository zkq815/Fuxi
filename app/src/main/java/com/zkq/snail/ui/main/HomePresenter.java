package com.zkq.snail.ui.main;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;


/**
 * Created by zkq
 * on 2018/1/23.
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
