package com.zkq.fuxi.ui.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.tencent.mmkv.MMKV;
import com.zkq.fuxi.ui.main.HomeActivity;
import com.zkq.fuxi.R;
import com.zkq.weapon.base.BaseActivity;
import com.zkq.weapon.market.glide.glideimpl.GlideImageLoader;

/**
 * @author:zkq
 * create:2018/10/24 上午11:45
 * email:zkq815@126.com
 * desc:
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActionBar(false, false, "");
        //全屏隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
