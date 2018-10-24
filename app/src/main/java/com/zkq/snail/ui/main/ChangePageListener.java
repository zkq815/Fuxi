package com.zkq.snail.ui.main;

import android.os.Parcelable;

/**
 * @author:zkq
 * create:2018/10/24 上午11:44
 * email:zkq815@126.com
 * desc:
 */

public interface ChangePageListener extends Parcelable {

    void changePage(int index);

    void changePage(boolean promotion, final int index, final String iconUrl, boolean isGif);
}
