package com.zkq.snail.ui.main;


import android.app.Activity;

import com.zkq.snail.base.mvp.BasePresenter;
import com.zkq.snail.base.mvp.BaseView;

/**
 * Created by ruhaiwen on 2018/1/23.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void setUpNavigation();

        boolean isAlive();

        Activity getActivity();
    }

    interface Presenter extends BasePresenter {

    }
}
