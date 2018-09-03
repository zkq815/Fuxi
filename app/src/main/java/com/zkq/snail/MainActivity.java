package com.zkq.snail;

import android.os.Bundle;

import com.zkq.snail.baseui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
