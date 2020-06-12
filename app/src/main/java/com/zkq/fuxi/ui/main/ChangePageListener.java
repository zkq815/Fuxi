package com.zkq.fuxi.ui.main;

import android.os.Parcelable;

/**
 * @author:zkq
 * create:2018/10/24 上午11:44
 * email:zkq815@126.com
 * desc: 页面更改的监听
 */

public interface ChangePageListener extends Parcelable {

    void changePage(int index);

    void changePage(boolean promotion, final int index, final String iconUrl, boolean isGif);
}
