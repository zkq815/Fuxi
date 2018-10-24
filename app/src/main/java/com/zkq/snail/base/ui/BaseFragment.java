package com.zkq.snail.base.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkq.snail.BuildConfig;
import com.zkq.snail.common.Constants;
import com.zkq.snail.util.XLog;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class BaseFragment extends Fragment
//        implements BaseActivity.INetChange
{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (netSensitive() && getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).addNetChangeListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (BuildConfig.LOG_DEBUG) {
            XLog.i(this.getClass().toString(), "onCreateView");
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        if (BuildConfig.LOG_DEBUG) {
            XLog.i(this.getClass().toString(), "onAttach");
        }

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        if (BuildConfig.LOG_DEBUG) {
            XLog.i(this.getClass().toString(), "onDetach");
        }

        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        if (BuildConfig.LOG_DEBUG) {
            XLog.i(this.getClass().toString(), "onDestroyView");
        }

        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).removeNetChangeListener(this);
        }

        super.onDestroy();
//        LeakWatcher.watch(this);
    }

    protected ActionBar getSupportActionBar() {
        final Activity activity = getActivity();
        if (null != activity && activity instanceof AppCompatActivity) {
            return ((AppCompatActivity) activity).getSupportActionBar();
        }

        return null;
    }

    /**
     * 设置ActionBar标题
     *
     * @param title 标题
     */
    protected void setTitle(final String title) {
        final Activity activity = getActivity();
        if (null != activity && !activity.isFinishing() && activity instanceof AppCompatActivity) {
            final ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (null != actionBar) {
                actionBar.setTitle(title);
            }
        }
    }

    /**
     * 设置ActionBar标题
     *
     * @param res 标题资源ID
     */
    protected void setTitle(final int res) {
        setTitle(getResources().getString(res));
    }

    /**
     * 是否要对网络变化敏感
     */
    protected boolean netSensitive() {
        return false;
    }

//    @Override
//    public void onNetChange(boolean connected) {
//
//    }

    public void startWebActivity(String url, int resId) {
        final Activity activity = getActivity();
        if (null != activity) {
            final String title = activity.getString(resId);
            final Intent it = new Intent(activity, WebViewPluginActivity.class);
            it.putExtra(Constants.INTENT_DATA_URL, url);
            it.putExtra(Constants.INTENT_DATA_TITLE, title);
            activity.startActivity(it);
        }
    }
}
