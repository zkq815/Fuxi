package com.zkq.fuxi.ui.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.zkq.fuxi.ui.main.MainActivity;
import com.zkq.fuxi.R;
import com.zkq.weapon.base.BaseActivity;

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
        //全屏隐藏状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
