package com.zkq.snail.ui.main;

import android.os.Parcelable;

/**
 * Created by zkq
 * on 2018/3/19.
 */

public interface ChangePageListener extends Parcelable {

    void changePage(int index);

    void changePage(boolean promotion, final int index, final String iconUrl, boolean isGif);
}
