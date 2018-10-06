package com.zkq.snail.base.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zkq
 * on 2018/8/26.
 * 基类Activity
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatebar();
    }

    /**
     * 设置顶部状态栏和底部虚拟操作栏
     * */
    private void setStatebar(){
        //判断系统是否是5.0以上
        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //状态栏设置为透明背景色
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //虚拟底部操作栏背景颜色设置
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }


}
