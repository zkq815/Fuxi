package com.zkq.fuxi.customview.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zkq.fuxi.R;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class NavigationItemView extends FrameLayout {

    private View mNormalView;
    private ImageView mIconView;
    private TextView mTitleView;
    private ImageView mBigIconView;

    public NavigationItemView(Context context) {
        this(context, null);
    }

    public NavigationItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.navigation_item_layout, this);
        mNormalView = findViewById(R.id.normal);
        mIconView = findViewById(R.id.nav_icon);
        mTitleView = findViewById(R.id.nav_title);
        mBigIconView = findViewById(R.id.big);
    }

    public void setUpView(@NonNull final NavigationItem navigationItem) {
        if (!TextUtils.isEmpty(navigationItem.url)) {
            mNormalView.setVisibility(GONE);
            mBigIconView.setVisibility(VISIBLE);
            if (navigationItem.gifMode) {
                Glide.with(getContext()).asGif().load(navigationItem.url).into(mBigIconView);
            } else {
                Glide.with(getContext()).load(navigationItem.url).into(mBigIconView);
            }

            mTitleView.setTextColor(getResources().getColor(R.color.black_alpha_60));
        } else {
            mNormalView.setVisibility(VISIBLE);
            mBigIconView.setVisibility(GONE);
            mIconView.setImageDrawable(getSelectorDrawable(getResources().getDrawable(navigationItem.icon), getResources().getDrawable(navigationItem.iconActivated)));

            mTitleView.setTextColor(getResources().getColorStateList(R.color.black));
        }
        mTitleView.setText(navigationItem.titleName);
    }

    public void setSelectedState(boolean isSelected) {
        mIconView.setSelected(isSelected);
        mTitleView.setSelected(isSelected);
    }

    private Drawable getSelectorDrawable(Drawable normalDrawable, Drawable pressedDrawable) {
        final StateListDrawable selector = new StateListDrawable();// 背景选择器
        selector.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
        selector.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        selector.addState(new int[]{}, normalDrawable);
        return selector;
    }
}
