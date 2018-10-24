package com.zkq.snail.ui.main.home;

import com.zkq.snail.base.mvp.BasePresenter;
import com.zkq.snail.base.mvp.BaseView;

/**
 * @author:zkq
 * create:2018/10/24 上午11:41
 * email:zkq815@126.com
 */
public interface DefaultContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }

}
