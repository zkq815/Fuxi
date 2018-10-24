package com.zkq.snail.customview.navigation;

import android.support.annotation.DrawableRes;
import android.util.SparseArray;

import com.zkq.snail.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkq
 * @since 2018/10/24
 */
public enum NavigationItem {
    //首页
    HOME("首页", R.drawable.nav_home, R.drawable.nav_home_activated),
    MEMORY("记忆", R.drawable.nav_category, R.drawable.nav_category_activated),
    EXPLORE("探索", R.drawable.nav_discovery, R.drawable.nav_discovery_activated),
    TOOLS("工具", R.drawable.nav_cart, R.drawable.nav_cart_activate),
    MINE("我的", R.drawable.nav_personal, R.drawable.nav_personal_activated);

    public final String name;

    @DrawableRes
    public final int icon;

    @DrawableRes
    public final int iconActivated;

    public final boolean enable;

    public boolean gifMode;

    public String url;

    private static final SparseArray<NavigationItem> map = new SparseArray<>();
    private static final List<NavigationItem> list = new ArrayList<>();

    static {
        for (NavigationItem navigationItem : values()) {
            map.put(navigationItem.type(), navigationItem);

            if (navigationItem.enable) {
                list.add(navigationItem);
            }
        }
    }

    public static NavigationItem[] get() {
        final NavigationItem[] array = new NavigationItem[list.size()];
        return list.toArray(array);
    }

    public static NavigationItem from(final int type) {
        return map.get(type);
    }

    NavigationItem(String name, int icon, int iconActivated) {
        this(name, icon, iconActivated, true);
    }

    NavigationItem(String name, int icon, int iconActivated, final boolean enable) {
        this.name = name;
        this.icon = icon;
        this.iconActivated = iconActivated;
        this.enable = enable;
    }

    public int type() {
        return ordinal();
    }

    public static void reset() {
        for (NavigationItem item : values()) {
            item.gifMode = false;
            item.url = null;
        }
    }

    @Override
    public String toString() {
        return "NavigationItem{" +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", iconActivated=" + iconActivated +
                '}';
    }
}
