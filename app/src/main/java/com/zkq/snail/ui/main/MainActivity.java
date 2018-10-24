package com.zkq.snail.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.zkq.snail.R;
import com.zkq.snail.base.ui.BaseActivity;
import com.zkq.snail.base.ui.BaseFragment;
import com.zkq.snail.customview.navigation.NavigationItem;
import com.zkq.snail.customview.navigation.NavigationWidget;
import com.zkq.snail.customview.viewpager.BasePagerAdapter;
import com.zkq.snail.customview.viewpager.ScrollableViewPager;
import com.zkq.snail.customview.viewpager.ViewPagerWrapper;
import com.zkq.snail.ui.main.explore.ExploreFragment;
import com.zkq.snail.ui.main.home.DefaultFragment;
import com.zkq.snail.ui.main.home.DefaultPresenter;
import com.zkq.snail.ui.main.memory.MemoryFragment;
import com.zkq.snail.ui.main.memory.MemoryPresenter;
import com.zkq.snail.ui.main.mine.MineFragment;
import com.zkq.snail.ui.main.mine.MinePresenter;
import com.zkq.snail.ui.main.tools.ToolsFragment;
import com.zkq.snail.ui.main.tools.ToolsPresenter;
import com.zkq.snail.ui.main.toolutil.CheckActivity;
import com.zkq.snail.ui.main.toolutil.CheckData;

import java.util.Arrays;
import java.util.List;

/**
 * @author:zkq
 * create:2018/10/24 上午11:46
 * email:zkq815@126.com
 * desc:
 */
public class MainActivity extends BaseActivity implements HomeContract.View, BasePagerAdapter.FragmentProducer{

    public static final String PAGE_TYPE = "page_type";

    private HomePresenter mHomePresenter;

    private HomeContract.Presenter mPresenter;
    private NavigationWidget mNavigationWidget;
    private ViewPagerWrapper<NavigationItem> mHomeViewPagerWrapper;
    private List<NavigationItem> mNavigationItemList = Arrays.asList(NavigationItem.get());
    /**
     * link跳转参数，定位到具体的tab页
     * */
    private int mPageType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mNavigationWidget = (NavigationWidget) findViewById(R.id.home_navigation_widget);

        BasePagerAdapter<NavigationItem> adapter = new BasePagerAdapter<>(getSupportFragmentManager(), this);
        mHomeViewPagerWrapper = new ViewPagerWrapper((ScrollableViewPager) findViewById(R.id.home_viewpager), 5);
        mHomeViewPagerWrapper.setAdapter(adapter);
        mHomeViewPagerWrapper.setPageScrollEnabled(false);

        mHomePresenter = new HomePresenter(this);

        mPresenter.subscribe();

        initTab(getIntent());
    }

    private void initTab(Intent intent) {
        if (intent == null) {
            return;
        }

        int pageIndex = intent.getIntExtra(PAGE_TYPE, mPageType);

        final Uri uri = intent.getData();
        if (uri != null) {
            final String page = uri.getQueryParameter("page");
            if (!TextUtils.isEmpty(page)) {
                try {
                    pageIndex = Integer.valueOf(page);
                } catch (Exception ignored) {
                }
            }
        }

        if (pageIndex < 0 || pageIndex >= mNavigationItemList.size()) {
            pageIndex = mPageType;
        }

        if (pageIndex != mPageType) {
            mPageType = pageIndex;
            mNavigationWidget.setSelectPosition(mPageType);
            mHomeViewPagerWrapper.setPageSelection(mPageType);
        }
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setUpNavigation() {
        initNavigationWidget();
        initViewPager();
    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigationWidget() {
        mNavigationWidget.setData(mNavigationItemList, getTabPosition(), new NavigationWidget.OnNavigationItemSelectListener() {
            @Override
            public void onSelected(int position) {
                mHomeViewPagerWrapper.setPageSelection(position, false);
                mNavigationWidget.setSelectPosition(position);
            }

            @Override
            public void reSelected(int position) {
                final Fragment fragment = mHomeViewPagerWrapper.getFragment(position);
                if (fragment instanceof OnPageListener) {
                    ((OnPageListener) fragment).onPageReselected();
                }
            }

        });
    }

    /**
     * 初始化 viewpager
     */
    private void initViewPager() {
        mHomeViewPagerWrapper.setDataSet(mNavigationItemList);
        mHomeViewPagerWrapper.setPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageType = position;

                Fragment fragment = mHomeViewPagerWrapper.getFragment(position);
                if (fragment instanceof OnPageListener) {
                    ((OnPageListener) fragment).onPageSelected();
                    mNavigationWidget.setSelectPosition(position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mHomeViewPagerWrapper.setPageSelection(getTabPosition(), false);
    }

    /**
     * 获取对应 tab 在 viewpager 中的 item 位置
     */
    private int getTabPosition() {
        if (!CheckData.hasData(mNavigationItemList)) {
            return 0;
        }
        final NavigationItem navigationItem = NavigationItem.from(mPageType);
        if (navigationItem != null) {
            return navigationItem.type();
        }

        return 0;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public boolean isAlive() {
        return CheckActivity.isActivityAlive(getActivity());
    }

    private final ChangePageListener changePageListener = new ChangePageListener() {

        @Override
        public void changePage(int index) {
            mHomeViewPagerWrapper.setPageSelection(index, false);
        }

        @Override
        public void changePage(boolean promotion, int index, String iconUrl, boolean isGif) {
            if (promotion) {
                mHomeViewPagerWrapper.setPageSelection(index, false);
            }
            mNavigationWidget.setIcon(NavigationItem.from(index), iconUrl, isGif);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }
    };

    @Override
    public Fragment produceFragment(int position) {
        BaseFragment fragment = null;
        switch (mNavigationItemList.get(position)) {
            case HOME: {
                DefaultFragment defaultfragment = new DefaultFragment();
                new DefaultPresenter(defaultfragment);
                fragment = defaultfragment;
            }
            break;
            case MEMORY: {
                MemoryFragment memoryFragment = new MemoryFragment();
                new MemoryPresenter(memoryFragment);
//                return memoryFragment;
                fragment = memoryFragment;
            }
                break;
            case EXPLORE: {
                ExploreFragment exploreFragment = new ExploreFragment();
                final Bundle bundle = new Bundle();
                bundle.putParcelable("listener", changePageListener);
                exploreFragment.setArguments(bundle);
//                return exploreFragment;
                fragment = exploreFragment;

            }
                break;
            case TOOLS: {
                ToolsFragment toolsFragment = new ToolsFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isActivity", false);
                bundle.putParcelable("listener", changePageListener);
                toolsFragment.setArguments(bundle);
                new ToolsPresenter(toolsFragment);
//                return toolsFragment;
                fragment = toolsFragment;
            }
                break;
            case MINE: {
                MineFragment mineFragment = new MineFragment();
                new MinePresenter(mineFragment);
//                return mineFragment;
                fragment = mineFragment;
            }
                break;
            default:
                DefaultFragment defaultfragment1 = new DefaultFragment();
                new DefaultPresenter(defaultfragment1);
//                return defaultfragment1;
                fragment = defaultfragment1;
            break;
        }

        return fragment;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
