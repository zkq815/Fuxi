package com.zkq.snail.customview.navigation;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.zkq.snail.R;
import com.zkq.snail.ui.main.toolutil.CheckData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkq
 * on 18-10-6.
 */

public class NavigationWidget extends RelativeLayout {

    private LinearLayout bar;

    private List<NavigationItemView> mItemViewList;
    private int mCurrentPosition = 0;

    public NavigationWidget(Context context) {
        this(context, null);
    }

    public NavigationWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(@NonNull final Context context) {
        inflate(context, R.layout.navigation_layout, this);
        setBackgroundColor(Color.TRANSPARENT);

        bar = findViewById(R.id.bar);
    }

    /**
     * 初始化 navigationWidget
     *
     * @param navigationItemList 导航数据
     * @param selectPosition     当前选中的 position
     * @param listener           回调
     */
    public void setData(List<NavigationItem> navigationItemList, int selectPosition, final OnNavigationItemSelectListener listener) {
        if (CheckData.hasData(navigationItemList)) {
            if (mItemViewList == null) {
                mItemViewList = new ArrayList<>();
            } else {
                mItemViewList.clear();
            }
            for (int i = 0; i < navigationItemList.size(); i++) {
                final int position = i;
                final NavigationItemView itemView = new NavigationItemView(getContext());
                itemView.setUpView(navigationItemList.get(i));
                itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            dealClickEvent(position, listener);
                        }
                    }
                });
                bar.addView(itemView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
                mItemViewList.add(itemView);
            }
            setSelectPosition(selectPosition);
        }
    }

    /**
     * 设置当前选中的 position
     *
     * @param position position
     */
    public void setSelectPosition(int position) {
        if (mItemViewList != null && position >= 0 && position < mItemViewList.size()) {
            mItemViewList.get(mCurrentPosition).setSelectedState(false);
            mItemViewList.get(position).setSelectedState(true);
            mCurrentPosition = position;
        }
    }

    /**
     * navigationWidget给外部的回调，用于切换页面
     */
    public interface OnNavigationItemSelectListener {
        void onSelected(int position);

        void reSelected(int position);
    }

    /**
     * 处理点击事件
     *
     * @param position position
     */
    private void dealClickEvent(int position, OnNavigationItemSelectListener listener) {
        if (mCurrentPosition == position) {
            listener.reSelected(position);
        } else {
            listener.onSelected(position);
        }
    }

    public void setIcon(@NonNull final NavigationItem item, String iconUrl, boolean isGif) {
        item.gifMode = isGif;
        item.url = iconUrl;
        mItemViewList.get(item.ordinal()).setUpView(item);
    }
}
