package com.zkq.fuxi.customview.navigation;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import com.zkq.fuxi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkq
 * @since 2018/10/24
 */
public enum NavigationItem {
    //首页
    HOME(R.string.main_main, R.drawable.nav_home, R.drawable.nav_home_activated),
    MEMORY(R.string.main_buddhism, R.drawable.nav_category, R.drawable.nav_category_activated),
    EXPLORE(R.string.main_prime, R.drawable.nav_discovery, R.drawable.nav_discovery_activated),
    TOOLS(R.string.main_taoism, R.drawable.nav_cart, R.drawable.nav_cart_activate),
    MINE(R.string.main_tool, R.drawable.nav_personal, R.drawable.nav_personal_activated);

    @StringRes
    public final int titleName;

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

    NavigationItem(int name, int icon, int iconActivated) {
        this(name, icon, iconActivated, true);
    }

    NavigationItem(int name, int icon, int iconActivated, final boolean enable) {
        this.titleName = name;
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
                ", name='" + titleName + '\'' +
                ", icon=" + icon +
                ", iconActivated=" + iconActivated +
                '}';
    }
}
